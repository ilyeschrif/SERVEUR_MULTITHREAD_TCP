package Service;

import java.io.Serializable;

public class Calculatrice implements Serializable {

private int oper1;
private  int oper2;
private String oper;

private int res;



public Calculatrice(int oper1,String oper,int oper2)
{
    // Constructeur pour initialiser les opérandes et l'opérateur
    this.oper1=oper1;
    this.oper2=oper2;
    this.oper=oper;
}

    // Méthodes pour définir les opérandes et l'opérateur
    public void setOper1(int oper1) {
        this.oper1 = oper1;
    }

    public void setOper2(int oper2)
    {
        this.oper2 = oper2;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    // Méthodes pour obtenir les opérandes et l'opérateur
    public int getOper1() {
        return oper1;
    }

    public int getOper2() {
        return oper2;
    }

    public String getOper()
    {
        return oper;
    }

    // Méthodes pour définir et obtenir le résultat du calcul
    public int setRes(int res)
    {
        return this.res=res;
    }
    public int getRes() {
        return res;
    }

}
