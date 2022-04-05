package twisk.outils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KitCTest {
    private KitC C;

    @Test
    void creerEnvironnemment1() {
        C = new KitC();
        C.creerEnvironnemment();
    }

    @Test
    void creerFichier1(){
        C = new KitC();
        C.creerFichier("#include \"def.h\"\n" +
                "\n" +
                "#define sasEntree 0\n" +
                "#define sasSortie 1\n" +
                "#define guichet 3\n" +
                "#define activite 2\n" +
                "\n" +
                "// 3 Etapes\n" +
                "\n" +
                "//La première activité (Autre que Sas) est le dernier numéro, et la dernière activité est la 2eme étape\n" +
                "\n" +
                "void simulation(int ids){\n" +
                "  entrer(sasEntree);\n" +
                "  delai(6,3);\n" +
                "  P(ids,guichet);\n" +
                "  transfert(sasEntree,guichet);\n" +
                "  delai(3,1);\n" +
                "  transfert(guichet,activite);\n" +
                "  V(ids,guichet);\n" +
                "  delai(4,2);\n" +
                "  transfert(activite,sasSortie);\n" +
                "  \n" +
                "}");
    }

    @Test
    void compiler1(){
        C = new KitC();
        C.compiler();
    }

    @Test
    void construireLaLibrairie1(){
        C = new KitC();
        C.construireLaLibrairie(String.valueOf(0));
    }
}