package silva.rafael.simplecalculator.calculator.states;

import java.math.BigDecimal;

import silva.rafael.simplecalculator.calculator.operations.CalculatorOperation;

// Classe: ResultState
// - Representa o estado de resultado de uma operação.
//
// Responsabilidade:
// - Disponibilizar o resultado de uma operação ao usuário ou a outras operações.
//
// Característica importante: Detalhe de Utilização
// - ResultState sempre é um estado intermediário que nunca obtém controle por um ciclo.
// - Isto acontece para garantir que nenhum dígito ou operação é ignorado.
// --> Ver classes: UnaryOperationState e BinaryOperationState.
// --> Ver classe:  SimpleCalculator.
//
// Possíveis entradas:
// - Qualquer dígito:   Inicia o estado de primeiro dígito de um número, repassando o dígito atual.
// - Qualquer operação: Inicia o estado de entrada de número com o resultado, repassa a operação.
//
// Transições de estado:
// - ResultState:             Mantém o fluxo de controle enquanto representa o resultado atual.
// - InitialNumberInputState: Quando a entrada de um novo número é iniciada, sem operações.
// - NumberInputState:        Quando uma nova operação é iniciada, utilizando o resultado atual.
//
// Resultados produzidos:
// Nenhum.
public final class ResultState implements CalculatorState {
    // Membro: nextState
    // - Guarda referências para transição de estado.
    // - Mantém o fluxo de controle enquanto representa o resultado atual.
    private CalculatorState nextState = this;

    // Membro: result
    // - Guarda o resultado de uma operação.
    // - Inicializado no construtor.
    private BigDecimal result;

    // Construtor: ResultState
    // - Guarda o resultado de uma operação, parâmetro input.
    public ResultState(BigDecimal input)
    {
        result = input;
    }

    // Método: getInputState
    // - Garante que o símbolo = é exibido para demonstrar resultados.
    // - Garante a diferenciação entre um resultado e a entrada de um número.
    @Override
    public String getInputState()
    {
        return "= " + result.toPlainString();
    }

    // Método: pushDigit
    // - Inicia o estado de entrada de um novo número e repassa o dígito ao mesmo.
    // - O resultado da operação anterior é descartado.
    @Override
    public void pushDigit(String digit)
    {
        nextState = new InitialNumberInputState();

        nextState.pushDigit(digit);
        nextState = nextState.getNextState();
    }

    // Método: pushOperation
    // - Inicia o estado de entrada de número com o resultado da operação anterior.
    // - Repassa a operação ao novo estado.
    @Override
    public void pushOperation(CalculatorOperation operation)
    {
        nextState = new NumberInputState(result.toPlainString());

        nextState.pushOperation(operation);
        nextState = nextState.getNextState();
    }

    // Método: getNextState
    // - Passa o controle ao novo estado de entrada de número.
    @Override
    public CalculatorState getNextState()
    {
        return nextState;
    }
}
