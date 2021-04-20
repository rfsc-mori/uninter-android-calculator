package silva.rafael.simplecalculator.calculator;

import silva.rafael.simplecalculator.calculator.operations.CalculatorOperation;
import silva.rafael.simplecalculator.calculator.states.CalculatorState;
import silva.rafael.simplecalculator.calculator.states.EmptyState;
import silva.rafael.simplecalculator.calculator.states.ErrorState;
import silva.rafael.simplecalculator.calculator.states.ResultState;

// Classe: SimpleCalculator
// - Executa as operações da calculadora e disponibiliza os estados da entrada e de resultado.
//
// Responsabilidade:
// - Manter o vínculo entre os estados da calculadora.
//
// Detalhes:
// - Implementa uma variação do state pattern.
public class SimpleCalculator
{
    // Membro: currentState
    // - Representa o estado atual da calculadora.
    // - Estado inicial: Estado vazio.
    private CalculatorState currentState = new EmptyState();

    // Método: hasError
    // - Indica se algum erro ocorreu.
    public boolean hasError()
    {
        return (currentState instanceof ErrorState);
    }

    // Método: hasResult
    // - Indica se o estado representa o resultado de uma operação.
    public boolean hasResult()
    {
        // ResultState sempre é um estado intermediário que nunca obtém controle por um ciclo.
        // Isto acontece para garantir que nenhum dígito ou operação é ignorado.
        // --> Ver classes: UnaryOperationState e BinaryOperationState.
        return (currentState.getNextState() instanceof ResultState);
    }

    // Método: getInputState
    // - Retorna o estado atual em forma de texto.
    public String getInputState()
    {
        return currentState.getInputState();
    }

    // Método: resetState
    // - Reinicia o estado da calculadora.
    public void resetState()
    {
        currentState = new EmptyState();
    }

    // Método: pushDigit
    // - Repassa a entrada de dígitos ao estado atual.
    public void pushDigit(String digit)
    {
        currentState.pushDigit(digit);
        currentState = currentState.getNextState();
    }

    // Método: pushOperation
    // - Repassa a entrada de operação ao estado atual.
    public void pushOperation(CalculatorOperation operation)
    {
        currentState.pushOperation(operation);
        currentState = currentState.getNextState();
    }
}
