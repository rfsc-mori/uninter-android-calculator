package silva.rafael.simplecalculator.calculator.operations;

import java.math.BigDecimal;

// Classe: OperationSub
// - Representa a operação de subtração.
//
// Responsabilidade:
// - Executar a subtração de dois números.
//
// Erros produzidos:
// - Nenhum.
public final class OperationSub implements BinaryOperation
{
    // Método: getSymbol
    // - Retorna o sinal de subtração.
    @Override
    public String getSymbol()
    {
        return "-";
    }

    // Método: hasError
    // - A subtração de dois números não gera erros. (BigDecimal.subtract)
    @Override
    public boolean hasError()
    {
        return false;
    }

    // Método: getErrorText
    // - Nunca produz um erro.
    @Override
    public String getErrorText()
    {
        return null;
    }

    // Método: process
    // - Retorna o resultado da subtração dos dois valores.
    @Override
    public BigDecimal process(BigDecimal leftHand, BigDecimal rightHand)
    {
        return leftHand.subtract(rightHand);
    }
}
