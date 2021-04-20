package silva.rafael.simplecalculator.calculator.states;

import silva.rafael.simplecalculator.calculator.operations.CalculatorOperation;
import silva.rafael.simplecalculator.calculator.operations.OperationSub;

// Classe: InitialNumberInputState
// - Representa o estado de entrada inicial de um número.
//
// Responsabilidade:
// - Garantir que o usuário consegue digitar números negativos.
//
// Possíveis entradas:
// - OperationSub (-): Representa o início de um número negativo.
// - Dígito ".":       Representa o início de um número decimal, adiciona 0 antes.
// - Dígitos 0-9:      Representa o início de um número.
//
// Transições de estado:
// - NumberInputState: Quando pelo menos um dígito já é representado.
// - ErrorState:       Quando recebe uma entrada inválida. (Ex: OperationMul (*))
// --> Ver classe NumberInputState.
//
// Resultados produzidos:
// --> Ver classe NumberInputState.
public class InitialNumberInputState extends NumberInputState
{
    // Membro: nextState
    // - Guarda referências para transição de estado.
    // --> Se null: ver método getNextState().
    private CalculatorState nextState = null;

    // Membro: sign
    // - Guarda a entrada do sinal do número.
    private String sign = "";

    // Método: getInputState
    // - Garante que o sinal, se digitado, é exibido durante a entrada inicial do número.
    // - Garante que a entrada de números negativos é destacada com parênteses.
    @Override
    public String getInputState()
    {
        String state = (sign + super.getInputState());

        if (!sign.isEmpty())
        {
            state = ("(" + state);
        }

        return state;
    }

    // Método: pushDigit
    // - Garante que o sinal é adicionado aos dígitos.
    // - Garante que 0 é adicionado à esquerda se o usuário pressionar "." (separador decimal).
    @Override
    public void pushDigit(String digit)
    {
        if (!sign.isEmpty())
        {
            super.pushDigit(sign);
        }

        if (".".equals(digit))
        {
            super.pushDigit("0");
        }

        super.pushDigit(digit);
    }

    // Método: pushOperation
    // - Trata OperationSub (-) como dígito (sinal).
    // - Garante que entradas como 1 e -1 sejam válidas.
    @Override
    public void pushOperation(CalculatorOperation operation)
    {
        if (operation instanceof OperationSub)
        {
            sign = "-";
        }
        else if (sign.isEmpty())
        {
            nextState = new ErrorState("Dígito ou sinal esperado.");
        }
        else
        {
            nextState = new ErrorState("Dígito esperado.");
        }
    }

    // Método: getNextState
    // - Passa o controle para um novo estado de entrada de número, se há pelo menos um dígito.
    // - Passa o controle para o estado de erro, se necessário.
    // --> Ver classe NumberInputState.
    @Override
    public CalculatorState getNextState()
    {
        if (nextState != null)
        {
            return nextState;
        }
        else if (super.inputLength() > 0)
        {
            return new NumberInputState(super.getRawInputState());
        }
        else
        {
            return super.getNextState();
        }
    }
}
