package silva.rafael.simplecalculator.calculator.operations;

import java.math.BigDecimal;

// Interface: UnaryOperation
// - Interface para implementação de operações unárias que aceitem argumentos da esquerda.
//
// Detalhes:
// - Toda operação que consuma um número ou resultado como entrada deve implementar esta interface.
public interface UnaryOperation extends CalculatorOperation
{
    // Método: process
    // - Deve consumir o valor de entrada (leftHand) e retornar o resultado do processamento deste.
    BigDecimal process(BigDecimal leftHand);
}
