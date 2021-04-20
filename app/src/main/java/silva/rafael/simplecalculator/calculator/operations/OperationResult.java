package silva.rafael.simplecalculator.calculator.operations;

import java.math.BigDecimal;

// Classe: OperationResult
// - Representa a operação de resultado.
//
// Responsabilidade:
// - Tratar o número da entrada como resultado.
//
// Erros produzidos:
// - Nenhum.
public final class OperationResult implements UnaryOperation
{
    // Método: getSymbol
    // - Retorna o sinal de igualdade.
    @Override
    public String getSymbol()
    {
        return "=";
    }

    // Método: hasError
    // - Representar o mesmo número não gera erros.
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
    // - Retorna o mesmo número de entrada como resultado.
    @Override
    public BigDecimal process(BigDecimal leftHand)
    {
        return leftHand;
    }
}
