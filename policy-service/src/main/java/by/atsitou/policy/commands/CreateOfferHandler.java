package by.atsitou.policy.commands;

import by.atsitou.command.bus.CommandHandler;
import by.atsitou.policy.domain.OfferFactory;
import by.atsitou.policy.domain.OfferRepository;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import by.atsitou.policy.service.api.v1.commands.createoffer.CreateOfferResult;
import by.atsitou.policy.service.api.v1.commands.createoffer.dto.ChoiceQuestionAnswer;
import by.atsitou.policy.service.api.v1.commands.createoffer.dto.NumericQuestionAnswer;
import by.atsitou.policy.service.api.v1.commands.createoffer.dto.TextQuestionAnswer;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.dto.QuestionAnswer;
import by.atsitou.policy.domain.Offer;
import by.atsitou.policy.infrastructure.adapters.restclient.PricingClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class CreateOfferHandler implements CommandHandler<CreateOfferResult, CreateOfferCommand> {

    private final OfferRepository offerRepository;
    private final PricingClient pricingOperations;

    @Transactional
    @Override
    public CreateOfferResult handle(CreateOfferCommand cmd) {
        CalculatePriceCommand calcPriceCmd = constructPriceCmd(cmd);
        CalculatePriceResult price = pricingOperations.calculatePrice(calcPriceCmd);
        Offer offer = OfferFactory.offerFromPrice(calcPriceCmd, price);
        offerRepository.save(offer);
        return constructResult(offer);
    }

    private CalculatePriceCommand constructPriceCmd(CreateOfferCommand cmd) {
        return new CalculatePriceCommand(
                cmd.getProductCode(),
                cmd.getPolicyFrom(),
                cmd.getPolicyTo(),
                cmd.getSelectedCovers(),
                constructAnswers(cmd.getAnswers()));
    }

    private CreateOfferResult constructResult(Offer offer) {
        return new CreateOfferResult(
                offer.getNumber(),
                offer.getTotalPrice(),
                offer.getCoversPrices());
    }

    private List<QuestionAnswer> constructAnswers(List<by.atsitou.policy.service.api.v1.commands.createoffer.dto.QuestionAnswer> answers) {
        List<QuestionAnswer> result = new ArrayList<>();
        for (by.atsitou.policy.service.api.v1.commands.createoffer.dto.QuestionAnswer answer : answers) {
            if (answer instanceof TextQuestionAnswer) {
                result.add(new by.atsitou.pricing.service.api.v1.commands.calculateprice.dto.TextQuestionAnswer(answer.getQuestionCode(), (String) answer.getAnswer()));
            } else if (answer instanceof ChoiceQuestionAnswer) {
                result.add(new by.atsitou.pricing.service.api.v1.commands.calculateprice.dto.ChoiceQuestionAnswer(answer.getQuestionCode(), (String) answer.getAnswer()));
            } else if (answer instanceof NumericQuestionAnswer) {
                result.add(new by.atsitou.pricing.service.api.v1.commands.calculateprice.dto.NumericQuestionAnswer(answer.getQuestionCode(), (BigDecimal) answer.getAnswer()));
            }
        }
        return result;
    }
}
