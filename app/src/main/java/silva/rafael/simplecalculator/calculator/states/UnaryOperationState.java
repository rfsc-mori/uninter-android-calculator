package silva.rafael.simplecalculator.calculator.states;

import java.math.BigDecimal;

import silva.rafael.simplecalculator.calculator.operations.CalculatorOperation;
import silva.rafael.simplecalculator.calculator.operations.UnaryOperation;

// Classe: UnaryOperationState
// - Representa a execução de uma operação unária que aceite argumentos da esquerda.
//
// Responsabilidade:
// - Executar uma operação unária.
//
// Característica importante: Construtor
// - Recebe o valor da esquerda (input) o qual será passado para a operação.
// - Executa a operação unária (currentOperation) passada no construtor do objeto.
// - Inicia o estado de resultado ou de erro.
//
// Possíveis entradas:
// - Qualquer dígito ou operação: Repassa ao próximo estado.
//
// Transições de estado:
// - ResultState: Após executar a operação no construtor.
// - ErrorState:  Quando a operação atual gera um erro.
//
// Resultados produzidos:
// - Um BigDecimal com o resultado da operação unária executada.
public final class UnaryOperationState implements CalculatorState
{
    // Membro: nextState
    // - Guarda referências para transição de estado.
    // - Inicializado no construtor.
    private CalculatorState nextState;

    // Construtor: UnaryOperationState
    // - Executa a operação unária com o parâmetro input.
    // - Inicia o estado de resultado ou de erro.
    UnaryOperationState(BigDecimal input, UnaryOperation currentOperation)
    {
        BigDecimal result = currentOperation.process(input);

        if (!currentOperation.hasError())
        {
            nextState = new ResultState(result);
        }
        else
        {
            nextState = new ErrorState(currentOperation.getErrorText());
        }
    }

    // Método: getInputState
    // - Retorna a representação textual do resultado.
    @Override
    public String getInputState()
    {
        return nextState.getInputState();
    }

    // Método: pushDigit
    // - Repassa o dígito ao próximo estado.
    @Override
    public void pushDigit(String digit)
    {
        nextState.pushDigit(digit);
        nextState = nextState.getNextState();
    }

    // Método: pushOperation
    // - Repassa a operação ao próximo estado.
    @Override
    public void pushOperation(CalculatorOperation operation)
    {
        nextState.pushOperation(operation);
        nextState = nextState.getNextState();
    }

    // Método: getNextState
    // - Passa o controle para o estado que representa o resultado da operação.
    // - Passa o controle para o estado de erro, se necessário.
    @Override
    public CalculatorState getNextState()
    {
        return nextState;
    }
}
