package silva.rafael.simplecalculator.calculator.states;

import silva.rafael.simplecalculator.calculator.operations.CalculatorOperation;

// Interface: CalculatorState
// - Interface para implementação dos estados da calculadora.
//
// Detalhes:
// - Todos os estados devem implementar esta interface.
public interface CalculatorState
{
    // Método: getInputState
    // - Deve retornar o estado atual das operações em forma de texto.
    // - Todos os estados devem retornar um texto de feedback ao usuário.
    String getInputState();

    // Método: pushDigit
    // - Adiciona um dígito ao estado atual.
    // - Somente estados de erro devem ignorar dígitos.
    // - O tipo String é escolhido em vez de char para facilitar possíveis formatos futuros.
    void pushDigit(String digit);

    // Método: pushOperation
    // - Adiciona uma operação ao estado atual.
    // - Somente estados de erro devem ignorar operações.
    void pushOperation(CalculatorOperation operation);

    // Método: getNextState
    // - Deve retornar o próximo estado a processar as entradas.
    // - É vital para o fluxo de controle da calculadora.
    // - Os estados podem repassar o controle a si mesmos retornando this.
    // - Somente estados de erro devem segurar o controle indefinidamente.
    // - Os estados nunca devem retornar null.
    CalculatorState getNextState();
}
