package silva.rafael.simplecalculator.calculator.states;

import java.math.BigDecimal;

import silva.rafael.simplecalculator.calculator.operations.BinaryOperation;
import silva.rafael.simplecalculator.calculator.operations.CalculatorOperation;
import silva.rafael.simplecalculator.calculator.operations.UnaryOperation;

// Classe: NumberInputState
// - Representa o estado de entrada de um número.
//
// Responsabilidade:
// - Possibilitar a entrada completa de um número.
//
// Possíveis entradas:
// - Dígito ".":  Adiciona o separador decimal ".", no máximo 1.
// - Dígitos 0-9: Adiciona os dígitos à direita.
//
// Transições de estado:
// - NumberInputState:     Mantém o fluxo de controle até que a entrada de número seja finalizada.
// - BinaryOperationState: Quando uma operação binária é iniciada.
// - UnaryOperationState:  Quando uma operação unária é iniciada.
// - ErrorState:           Quando recebe uma entrada inválida. (Ex: 0.12.3)
//
// Resultados produzidos:
// - Um BigDecimal equivalente à representação textual do número entrado pelo usuário.
public class NumberInputState implements CalculatorState
{
    // Membro: nextState
    // - Guarda referências para transição de estado.
    // - Mantém o fluxo de controle enquanto o número está sendo digitado.
    private CalculatorState nextState = this;

    // Membro: inputState
    // - Auxiliar para construir a representação textual do número.
    private StringBuilder inputState = new StringBuilder();

    // Método: getRawInputState
    // - Retorna os dígitos da representação atual do número.
    protected final String getRawInputState()
    {
        return inputState.toString();
    }

    // Método: inputLength
    // - Retorna o número de dígitos representados atualmente.
    protected final int inputLength()
    {
        return inputState.length();
    }

    // Construtor: NumberInputState
    // - Constrói um novo estado de entrada de número, vazio.
    // - Este construtor somente deve ser utilizado por estados que o manipulam diretamente.
    // - O estado InitialNumberInputState deve ser utilizado para construir um número
    //   à partir da entrada do usuário.
    public NumberInputState()
    {
    }

    // Construtor: NumberInputState
    // - Constrói um novo estado de entrada de número à partir de uma representação textual.
    // - Este construtor deve ser utilizado por outros estados que o manipulem diretamente.
    // - Não há validação do argumento, o tratamento de erro é adiado para quando o número
    //   é requisitado via getNumber().
    public NumberInputState(String currentInput)
    {
        inputState.append(currentInput);
    }

    // Método: getInputState
    // - Retorna a representação textual do número atual.
    // - Garante que a entrada de números negativos é destacada com parênteses.
    @Override
    public String getInputState()
    {
        String state = inputState.toString();

        if (state.startsWith("-"))
        {
            state = "(" + state + ")";
        }

        return state;
    }

    // Método: getNumber
    // - Retorna um BigDecimal construído à partir dos dígitos.
    // - O BigDecimal é usado por causa da precisão arbitrária já que a entrada vem do usuário.
    // - O caso "0." (separador decimal, mas sem casas decimais) é tratado pelo BigDecimal.
    // - A interpretação do número entrado pode falhar, exceptions devem ser tratadas pelo caller.
    public final BigDecimal getNumber()
    {
        return new BigDecimal(inputState.toString()).stripTrailingZeros();
    }

    // Método: pushDigit
    // - Adiciona o dígito à direita do número.
    // - Garante que a representação textual somente contenha um separador decimal ".".
    @Override
    public void pushDigit(String digit)
    {
        if (".".equals(digit))
        {
            if (inputState.indexOf(digit) == -1)
            {
                inputState.append(digit);
            }
            else
            {
                nextState = new ErrorState("Separador decimal já existe.");
            }
        }
        else
        {
            inputState.append(digit);
        }
    }

    // Método: pushOperation
    // - Inicia a execução de uma operação e a passa o controle.
    // - Garante que a operação somente será executada se a entrada do número é válida.
    @Override
    public void pushOperation(CalculatorOperation operation)
    {
        BigDecimal leftHand;

        try
        {
            leftHand = getNumber();
        }
        catch (NumberFormatException exception)
        {
            nextState = new ErrorState("Formato de número inválido.");
            return;
        }

        if (operation instanceof BinaryOperation)
        {
            nextState = new BinaryOperationState(leftHand, (BinaryOperation)operation);
        }
        else if (operation instanceof UnaryOperation)
        {
            nextState = new UnaryOperationState(leftHand, (UnaryOperation)operation);
        }
        else
        {
            nextState = new ErrorState("Operação não suportada.");
        }
    }

    // Método: getNextState
    // - Passa o controle para o estado de execução de operação, se iniciado.
    // - Passa o controle para o estado de erro, se necessário.
    @Override
    public CalculatorState getNextState()
    {
        return nextState;
    }
}
