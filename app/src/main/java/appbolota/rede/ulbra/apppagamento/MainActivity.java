package appbolota.rede.ulbra.apppagamento;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgroup;
    Button btnCalcular;
    EditText edtNomeFunc;
    EditText edtSalarioBruto;
    EditText edtNumFilhos;
    RadioButton rbtM;
    RadioButton rbtF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNomeFunc = (EditText) findViewById(R.id.edtNomeFunc);
        edtSalarioBruto = (EditText) findViewById(R.id.edtSalarioBruto);
        edtNumFilhos = (EditText) findViewById(R.id.edtNumFilhos);
        rgroup = (RadioGroup) findViewById(R.id.rgroup);
        rbtM = (RadioButton) findViewById(R.id.rbtMasculino);
        rbtF = (RadioButton) findViewById(R.id.rbtFeminino);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String funcionario = edtNomeFunc.getText().toString();
                double salarioB = Double.parseDouble(edtSalarioBruto.getText().toString());
                int nFilhos = Integer.parseInt(edtNumFilhos.getText().toString());

                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Folha Pagamento");
                dialogo.setMessage(seletorSexo()+" o seu salário bruto é de: " + String.valueOf(salarioB)+
                        "\n Filhos: " + String.valueOf(nFilhos)+" "+
                        "\n INSS de R$"+inss(salarioB)+
                        "\n IR de R$"+ir(salarioB)+
                        "\n Sálario fámilia de: R$"+salFamilia(salarioB, nFilhos)+
                        "\n Sálario líquido de: R$"
                        +(salarioB - inss(salarioB) - ir(salarioB)+salFamilia(salarioB,nFilhos))
                );

                dialogo.show();
            }
        });

    }

    public double inss(double salario) {
        double novoSalario = 0;
        if (salario <= 1212.00) {
            novoSalario = salario * 0.075;
        } else if (salario >= 1212.01 && salario <= 2427.35) {
            novoSalario = salario * 0.09;
        } else if (salario >= 2427.36 && salario <= 3641.03) {
            novoSalario = salario * 0.12;
        } else {
            novoSalario = salario * 0.14;
        }
        return novoSalario;
    }

    public double ir(double salario) {
        double ss = 0;
        if (salario <= 1903.98) {
            ss = salario * 0;
        } else if (salario >= 1903.99 && salario <= 2826.65) {
            ss = salario * 0.075;
        } else if (salario >= 2826.66 && salario <= 3751.05) {
            ss = salario * 0.15;
        } else {
            ss = salario * 0.225;
        }
        return ss;
    }

    public String seletorSexo() {
        String sexo = null;
        if (rbtM.isChecked()) {
            sexo = "Sr. " + edtNomeFunc.getText().toString();
        } else {
            sexo = "Sra. " + edtNomeFunc.getText().toString();
        }
        return sexo;
    }

    public double salFamilia(double salario, int filho) {
        double sf = 0;

        if (salario <= 1212.00) {
            sf = filho * 56.47;
        }
        return sf;
    }


}
