package silva.rafael.simplecalculator.calculator.operations;

import java.math.BigDecimal;

// Classe: OperationMul
// - Representa a operação de multiplicação.
//
// Responsabilidade:
// - Executar a multiplicação de dois números.
//
// Erros produzidos:
// - Nenhum.
public final class OperationMul implements BinaryOperation
{
    // Método: getSymbol
    // - Retorna o sinal de multiplicação.
    @Override
    public String getSymbol()
    {
        return "*";
    }

    // Método: hasError
    // - A multiplicação de dois números não gera erros. (BigDecimal.multiply)
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
    // - Retorna o resultado da multiplicação dos dois valores.
    @Override
    public BigDecimal process(BigDecimal leftHand, BigDecimal rightHand)
    {
        return leftHand.multiply(rightHand);
    }
}
