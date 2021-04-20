package silva.rafael.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import silva.rafael.simplecalculator.calculator.SimpleCalculator;
import silva.rafael.simplecalculator.calculator.operations.OperationAdd;
import silva.rafael.simplecalculator.calculator.operations.OperationSub;
import silva.rafael.simplecalculator.calculator.operations.OperationMul;
import silva.rafael.simplecalculator.calculator.operations.OperationDiv;
import silva.rafael.simplecalculator.calculator.operations.OperationResult;

// Classe: MainActivity
// - Atividade principal do aplicativo.
//
// Responsabilidade:
// - Interação com o usuário.
//
// Detalhes:
// - Trata os eventos de interação com usuário e os traduz para chamadas
//   de métodos da classe SimpleCalculator (membro: calculator).
public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Membro: calculator
    // - Recebe as operações e as processa para obter o resultado.
    private SimpleCalculator calculator = new SimpleCalculator();

    // Membro: updateDisplay
    // - Atualiza o display com o texto que representa o estado de entrada.
    private void updateDisplay()
    {
        // Trata o display de erro separado do display de entrada e resultado.
        final TextView display      = findViewById(R.id.txtDisplay);
        final TextView errorDisplay = findViewById(R.id.txtErrorDisplay);

        if (!calculator.hasError())
        {
            display.setText(calculator.getInputState());

            display.setVisibility(View.VISIBLE);
            errorDisplay.setVisibility(View.INVISIBLE);

            // Alinha o texto do display à esquerda ao representar números ou operações longas.
            if (display.getLineCount() <= 1)
            {
                display.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            }
            else
            {
                display.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            }
        }
        else
        {
            errorDisplay.setText(calculator.getInputState());

            display.setVisibility(View.INVISIBLE);
            errorDisplay.setVisibility(View.VISIBLE);
        }
    }

    // Método: calcFunction
    // - Processa cliques nos botões de função especial.
    public void calcFunction(View view)
    {
        switch (view.getId())
        {
            case R.id.btnFnReset:
                calculator.resetState();
                break;
        }

        updateDisplay();
    }

    // Método: calcOperation
    // - Processa cliques nos botões de operação.
    public void calcOperation(View view)
    {
        switch (view.getId())
        {
            case R.id.btnOpAdd:
                calculator.pushOperation(new OperationAdd());
                break;

            case R.id.btnOpSub:
                calculator.pushOperation(new OperationSub());
                break;

            case R.id.btnOpMul:
                calculator.pushOperation(new OperationMul());
                break;

            case R.id.btnOpDiv:
                calculator.pushOperation(new OperationDiv());
                break;

            case R.id.btnFnResult:
                calculator.pushOperation(new OperationResult());
                break;
        }

        updateDisplay();
    }

    // Método: calcOperation
    // - Processa cliques nos botões numéricos.
    public void calcInputDigit(View view)
    {
        switch (view.getId())
        {
            case R.id.btnNumOne:
                calculator.pushDigit("1");
                break;

            case R.id.btnNumTwo:
                calculator.pushDigit("2");
                break;

            case R.id.btnNumThree:
                calculator.pushDigit("3");
                break;

            case R.id.btnNumFour:
                calculator.pushDigit("4");
                break;

            case R.id.btnNumFive:
                calculator.pushDigit("5");
                break;

            case R.id.btnNumSix:
                calculator.pushDigit("6");
                break;

            case R.id.btnNumSeven:
                calculator.pushDigit("7");
                break;

            case R.id.btnNumEight:
                calculator.pushDigit("8");
                break;

            case R.id.btnNumNine:
                calculator.pushDigit("9");
                break;

            case R.id.btnNumZero:
                calculator.pushDigit("0");
                break;

            case R.id.btnDot:
                calculator.pushDigit(".");
                break;
        }

        updateDisplay();
    }
}
