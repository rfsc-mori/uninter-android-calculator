package silva.rafael.simplecalculator.calculator.states;

import java.math.BigDecimal;

import silva.rafael.simplecalculator.calculator.operations.CalculatorOperation;
import silva.rafael.simplecalculator.calculator.operations.OperationResult;
import silva.rafael.simplecalculator.calculator.operations.UnaryOperation;

// Classe: EmptyState
// - Representa o estado vazio da calculadora.
//
// Responsabilidade:
// - Garantir que 0 é mostrado no display se nenhuma operação foi efetuada.
//
// Possíveis entradas:
// - OperationResult (=): Permite essa operação durante o estado vazio, retorna 0.
// --> Ver classe InitialNumberInputState.
//
// Transições de estado:
// - UnaryOperationState: Quando a operação de resultado é executada.
// --> Ver classe InitialNumberInputState.
//
// Resultados produzidos:
// - 0 se nenhuma operação foi efetuada.
// --> Se uma operação foi efetuada: Ver classe InitialNumberInputState.
public final class EmptyState extends InitialNumberInputState
{
    // Membro: nextState
    // - Guarda referências para transição de estado.
    // --> Se null: ver classe InitialNumberInputState.
    private CalculatorState nextState = null;

    // Método: getInputState
    // - Garante que 0 é retornado como feedback se nenhuma operação foi efetuada.
    @Override
    public String getInputState()
    {
        String state = super.getInputState();

        if (!state.isEmpty())
        {
            return state;
        }
        else
        {
            return "0";
        }
    }

    // Método: pushOperation
    // - Garante que 0 é retornado como feedback se a operação de resultado é executada.
    @Override
    public void pushOperation(CalculatorOperation operation)
    {
        if (operation instanceof OperationResult)
        {
            nextState = new UnaryOperationState(BigDecimal.ZERO, (UnaryOperation)operation);
        }
        else
        {
            super.pushOperation(operation);
        }
    }

    // Método: getNextState
    // - Passa o controle para o estado com a operação de resultado, se executada.
    // --> Ver classe InitialNumberInputState.
    @Override
    public CalculatorState getNextState()
    {
        if (nextState != null)
        {
            return nextState;
        }
        else
        {
            return super.getNextState();
        }
    }
}
