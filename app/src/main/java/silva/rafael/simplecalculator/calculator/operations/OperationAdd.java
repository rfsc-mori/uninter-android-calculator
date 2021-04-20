package silva.rafael.simplecalculator.calculator.operations;

import java.math.BigDecimal;

// Classe: OperationAdd
// - Representa a operação de adição.
//
// Responsabilidade:
// - Executar a adição de dois números.
//
// Erros produzidos:
// - Nenhum.
public final class OperationAdd implements BinaryOperation
{
    // Método: getSymbol
    // - Retorna o sinal de adição.
    @Override
    public String getSymbol()
    {
        return "+";
    }

    // Método: hasError
    // - A adição de dois números não gera erros. (BigDecimal.add)
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
    // - Retorna o resultado da adição dos dois valores.
    @Override
    public BigDecimal process(BigDecimal leftHand, BigDecimal rightHand)
    {
        return leftHand.add(rightHand);
    }
}
