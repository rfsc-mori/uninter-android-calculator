package silva.rafael.simplecalculator.calculator.operations;

import java.math.BigDecimal;

// Interface: BinaryOperation
// - Interface para implementação de operações binárias que aceitem valores da esquerda e direita.
//
// Detalhes:
// - Toda operação que consuma dois números como entrada deve implementar esta interface.
public interface BinaryOperation extends CalculatorOperation
{
    // Método: process
    // - Deve consumir os valores de entrada (leftHand e rightHand) e retornar um resultado.
    BigDecimal process(BigDecimal leftHand, BigDecimal rightHand);
}
