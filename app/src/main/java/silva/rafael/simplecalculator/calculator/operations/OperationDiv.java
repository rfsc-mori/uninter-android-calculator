package silva.rafael.simplecalculator.calculator.operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Classe: OperationDiv
// - Representa a operação de divisão.
//
// Responsabilidade:
// - Executar a divisão de dois números.
//
// Erros produzidos:
// - Divisão por zero: A divisão de um número por zero não é representável.
public final class OperationDiv implements BinaryOperation
{
    private boolean error = false;
    private String errorText = "";

    // Método: getSymbol
    // - Retorna o sinal de divisão.
    @Override
    public String getSymbol()
    {
        return "/";
    }

    // Método: hasError
    // - A divisão de dois números pode gerar erros. (BigDecimal.divide)
    @Override
    public boolean hasError()
    {
        return error;
    }

    // Método: getErrorText
    // - Retorna a descrição do erro gerado pela operação, se ocorrido.
    @Override
    public String getErrorText()
    {
        return errorText;
    }

    // Método: process
    // - Retorna o resultado da divisão dos dois valores.
    // - Garante que a operação de divisão não aconteça se o valor da direita é zero.
    // - Garante que a representação do resultado tenha um número máximo de casas decimais.
    @Override
    public BigDecimal process(BigDecimal leftHand, BigDecimal rightHand)
    {
        if (rightHand.compareTo(BigDecimal.ZERO) == 0)
        {
            error = true;
            errorText = "Divisão por zero.";

            return BigDecimal.ZERO;
        }

        // Decide a precisão de acordo com o número de dígitos de uma calculadora de bolso.
        final int pocketCalculatorDigitCount = 8;
        final int nonDecimalDigitsWhenZero   = 2;
        final int roundingScale = Math.abs(pocketCalculatorDigitCount - nonDecimalDigitsWhenZero);

        return leftHand.divide(rightHand, roundingScale, RoundingMode.HALF_UP);
    }
}
