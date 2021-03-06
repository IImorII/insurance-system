package by.atsitou.pricing.commands;

import by.atsitou.command.bus.CommandHandler;
import by.atsitou.pricing.domain.Calculation;
import by.atsitou.pricing.domain.Tariff;
import by.atsitou.pricing.domain.Tariffs;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.dto.QuestionAnswer;

import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import io.micronaut.transaction.annotation.ReadOnly;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class CalculatePriceHandler implements CommandHandler<CalculatePriceResult, CalculatePriceCommand> {

    private final Tariffs tariffs;

    @ReadOnly
    public CalculatePriceResult handle(CalculatePriceCommand calculatePriceCommand) {
        Tariff tariff = tariffs.getByCode(calculatePriceCommand.getProductCode());
        Calculation calculation = tariff.calculatePrice(toCalculation(calculatePriceCommand));

        return resultFromCalculation(calculation);
    }

    private Calculation toCalculation(CalculatePriceCommand calculatePriceCommand) {
        return new Calculation(
                calculatePriceCommand.getProductCode(),
                calculatePriceCommand.getPolicyFrom(),
                calculatePriceCommand.getPolicyTo(),
                calculatePriceCommand.getSelectedCovers(),
                calculatePriceCommand.getAnswers().stream()
                        .collect(Collectors.toMap(QuestionAnswer::getQuestionCode, QuestionAnswer::getAnswer))
        );
    }

    private CalculatePriceResult resultFromCalculation(Calculation calculation) {
        return new CalculatePriceResult(
                calculation.getTotalPremium(),
                calculation.getCovers().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getPrice()))
        );
    }
}
