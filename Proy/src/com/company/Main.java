package com.company;

import generated.PParser;
import generated.PScanner;
import com.company.PPrint;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PScanner scanner = null;
        PParser parser = null;
        PPrint pPrint= null;
        try {
            ANTLRInputStream input = new ANTLRInputStream(new FileReader("prueba.txt"));// se hace el input con la direccion de archivo
            scanner = new PScanner(input);// nuevo scanner con el input


            List<Token> lista = (List<Token>) scanner.getAllTokens();//se crea la lista,devuelve la una lista

            for (Token t : lista)
                System.out.println(t.getType() + " : '" + t.getText()+"'");

            scanner.reset();
            scanner = new PScanner(input);
            CommonTokenStream tokens = new CommonTokenStream(scanner);
            parser = new PParser(tokens);
            parser.program();
            pPrint = new PPrint();


            ParseTree raiz = parser.program();
            pPrint.visit(raiz);
            //System.out.println(raiz.toStringTree(parser));
        }
        catch(Exception e){
            System.out.println("No hay archivo");
        }
        new menu();
    }
}
