package silva.rafael.simplecalculator.calculator.states;

import java.math.BigDecimal;

import silva.rafael.simplecalculator.calculator.operations.BinaryOperation;
import silva.rafael.simplecalculator.calculator.operations.CalculatorOperation;

// Classe: BinaryOperationState
// - Representa a execução de uma operação binária.
//
// Responsabilidade:
// - Executar uma operação binária.
//
// Característica importante: Construtor
// - Recebe o valor da esquerda (input) o qual será passado para a operação.
// - Recebe a operação binária (operation).
//
// Possíveis entradas:
// - Qualquer dígito:   Repassa ao estado de entrada de número.
// - Qualquer operação: A) Quando é o primeiro dígito: Repassa ao estado de entrada de número.
//                      B) Faz a operação atual, cria o estado de resultado, repassa nova operação.
//
// Transições de estado:
// - BinaryOperationState: Mantém o fluxo de controle até que a operação seja finalizada.
// - ResultState:          Após executar a operação atual, antes da próxima operação iniciar.
// - ErrorState:           Quando o estado de entrada é inválido ou a operação atual gera um erro.
//
// Resultados produzidos:
// - Um BigDecimal com o resultado da operação binária executada.
public final class BinaryOperationState implements CalculatorState
{
    // Membro: nextState
    // - Guarda referências para transição de estado.
    // - Mantém o fluxo de controle até que a operação seja finalizada.
    private CalculatorState nextState = this;

    // Membro: leftHand
    // - Guarda o valor da esquerda para a operação binária.
    // - Inicializado no construtor.
    private BigDecimal leftHand;

    // Membro: currentOperation
    // - Guarda a operação binária a ser executada.
    // - Inicializado no construtor.
    private BinaryOperation currentOperation;

    // Membro: rightHandState
    // - Guarda o estado de entrada de número para obter o valor da direita.
    private CalculatorState rightHandState = new InitialNumberInputState();

    // Construtor: BinaryOperationState
    // - Guarda o valor da esquerda e a operação a ser executada.
    BinaryOperationState(BigDecimal input, BinaryOperation operation)
    {
        leftHand = input;
        currentOperation = operation;
    }

    // Método: getInputState
    // - Retorna a representação textual da operação. (Ex: (-0.5)*2.5)
    // - Garante que números negativos são destacados com parênteses.
    @Override
    public String getInputState()
    {
        String leftHandNumber  = leftHand.toPlainString();
        final String symbol    = currentOperation.getSymbol();
        final String rightHand = rightHandState.getInputState();

        if (leftHand.compareTo(BigDecimal.ZERO) < 0)
        {
            leftHandNumber = "(" + leftHandNumber + ")";
        }

        return leftHandNumber + symbol + rightHand;
    }

    // Método: pushDigit
    // - Repassa o dígito ao estado de entrada de número para o valor da direita.
    @Override
    public void pushDigit(String digit)
    {
        rightHandState.pushDigit(digit);
        rightHandState = rightHandState.getNextState();

        if (rightHandState instanceof ErrorState)
        {
            nextState = rightHandState;
        }
    }

    // Método: pushOperation
    // - Quando é o primeiro dígito: Repassa a operação ao estado de entrada de número, trata sinal.
    // - Valor da direita definido:  Executa a operação atual e obtém o resultado.
    // - Garante que a operação somente será executada se a entrada do número da direita é válida.
    // - Inicia o estado de resultado ou de erro e o repassa a nova operação.
    @Override
    public void pushOperation(CalculatorOperation operation)
    {
        if (rightHandState instanceof InitialNumberInputState)
        {
            rightHandState.pushOperation(operation);
            rightHandState = rightHandState.getNextState();

            if (rightHandState instanceof ErrorState)
            {
                nextState = rightHandState;
            }
        }
        else if (rightHandState instanceof NumberInputState)
        {
            BigDecimal rightHand;

            try
            {
                NumberInputState rightHandInput = (NumberInputState)rightHandState;
                rightHand = rightHandInput.getNumber();
            }
            catch (NumberFormatException exception)
            {
                nextState = new ErrorState("Formato de número inválido.");
                return;
            }

            BigDecimal result = currentOperation.process(leftHand, rightHand);

            if (!currentOperation.hasError())
            {
                nextState = new ResultState(result);
            }
            else
            {
                nextState = new ErrorState(currentOperation.getErrorText());
            }

            nextState.pushOperation(operation);
            nextState = nextState.getNextState();
        }
        else
        {
            nextState = new ErrorState("Erro interno.");
        }
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
