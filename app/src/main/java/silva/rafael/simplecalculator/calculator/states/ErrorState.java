package silva.rafael.simplecalculator.calculator.states;

import silva.rafael.simplecalculator.calculator.operations.CalculatorOperation;

// Classe: ErrorState
// - Representa um estado de erro com uma mensagem.
//
// Responsabilidade:
// - Relatar erros ao usuário.
//
// Possíveis entradas:
// - Nenhuma.
//
// Transições de estado:
// - Nenhuma.
//
// Resultados produzidos:
// - Nenhum.
public final class ErrorState implements CalculatorState
{
    // Membro: errorPrefix
    // - Guarda o prefixo das mensagens de erro.
    private final String errorPrefix = "Erro:\n";

    // Membro: errorText
    // - Guarda a mensagem de erro a ser exibida.
    // - Inicializado no construtor.
    private String errorText;

    // Construtor: ErrorState
    // - Guarda a mensagem de erro que representa o estado de erro atual.
    public ErrorState(String message)
    {
        errorText = errorPrefix + message;
    }

    // Método: getInputState
    // - Retorna a mensagem de erro que representa o estado de erro atual.
    @Override
    public String getInputState()
    {
        return errorText;
    }

    // Método: pushDigit
    // - Ignora dígitos durante o estado de erro.
    @Override
    public void pushDigit(String digit)
    {
    }

    // Método: pushOperation
    // - Ignora operações durante o estado de erro.
    @Override
    public void pushOperation(CalculatorOperation operation)
    {
    }

    // Método: getNextState
    // - Mantém o fluxo de controle indefinidamente.
    // - O estado de operação atual deve ser descartado e reiniciado com um EmptyState.
    @Override
    public CalculatorState getNextState()
    {
        return this;
    }
}
