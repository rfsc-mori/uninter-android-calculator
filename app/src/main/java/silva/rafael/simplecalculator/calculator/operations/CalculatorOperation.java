package silva.rafael.simplecalculator.calculator.operations;

// Interface: CalculatorOperation
// - Interface para implementação das operações da calculadora.
//
// Detalhes:
// - Toda (sub)interface de operação deve implementar esta interface.
// - Toda operação deve implementar apenas uma subinterface.
// - Esta interface não deve ser implementada diretamente por operações.
// --> Ver interfaces: UnaryOperation e BinaryOperation
public interface CalculatorOperation
{
    // Método: getSymbol
    // - Deve retornar o símbolo que representa a operação.
    // - Toda operação deve retornar um símbolo único que a represente e diferencie.
    String getSymbol();

    // Método: hasError
    // - Deve indicar se algum erro relacionado à operação ocorreu.
    // - Erros naturais da operação devem ser reportados retornando true no método hasError().
    // - Erros naturais da aplicação devem ser reportados através de exceptions.
    boolean hasError();

    // Método: getErrorText
    // - Deve retornar a descrição do erro, se ocorrido.
    // - Toda operação que pode gerar erros deve retornar uma String válida (non-null).
    // - Operações que nunca geram erros podem retornar null. (hasError() sempre == false)
    String getErrorText();
}
