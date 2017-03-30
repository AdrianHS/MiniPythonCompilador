package com.company;

import generated.PParser;
import generated.PParserBaseVisitor;

/**
 * Created by ADRIAN on 29/3/2017.
 */
public class PPrint extends PParserBaseVisitor {
/*    @Override
    public Object visitProgrm(PParser.ProgrmContext ctx) {
        return super.visitProgrm(ctx);
    }*/
    int cont = 0;
    private void printtab(int n)
    {
        for(int num=n; num != 0; num--) {
            System.out.print("+++");
        }
        System.out.print(">");
    }

    @Override
    public Object visitDefStatmm(PParser.DefStatmmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
        cont++;
        visit(ctx.defStatement());
        cont--;

        return null;

    }

    @Override
    public Object visitIfStatmm(PParser.IfStatmmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.ifStatement());

        cont--;

        return null;
    }

    @Override
    public Object visitRetunStatmm(PParser.RetunStatmmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.returnStatement());
        cont--;

        return null;
    }

    @Override
    public Object visitPrintStatmm(PParser.PrintStatmmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.printStatement());

        cont--;

        return null;
    }

    @Override
    public Object visitWhileStatmm(PParser.WhileStatmmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.whileStatement());

        cont--;

        return null;
    }

    @Override
    public Object visitAssignStatmm(PParser.AssignStatmmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.assignStatement());
        cont--;

        return null;
    }

    @Override
    public Object visitFunctionStatmm(PParser.FunctionStatmmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.functionCallStatement());

        cont--;

        return null;
    }

    @Override
    public Object visitDefStatm(PParser.DefStatmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName()+" : "+ctx.IDENTIFIER().getSymbol().getText());
//crear Jtextarea
        cont++;
        visit(ctx.argList());
        visit(ctx.sequence());
        cont--;

        return null;

    }

    @Override
    public Object visitArgListt(PParser.ArgListtContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName()+" : "+ctx.IDENTIFIER().getSymbol().getText());
//crear Jtextarea
        cont++;
        visit(ctx.moreArgs());
        cont--;

        return null;

    }

    @Override
    public Object visitArgVacio(PParser.ArgVacioContext ctx) {
         return null;

    }

    @Override
    public Object visitMoreArgss(PParser.MoreArgssContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
        cont++;
        for (int i=0; i <= ctx.IDENTIFIER().size()-1; i++)
        {
            System.out.println(ctx.IDENTIFIER(i));
            //visit(ctx.IDENTIFIER(i));
        }

        cont--;
//crear Jtextarea
       //cont++;
      //  visit(ctx.argList());
        //cont--;

        return null;

    }

    @Override
    public Object visitIfStatm(PParser.IfStatmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.expression());
        visit(ctx.sequence(0));
        visit(ctx.sequence(1));
        cont--;

        return null;
    }

    @Override
    public Object visitWhileStatm(PParser.WhileStatmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.expression());
        visit(ctx.sequence());
        cont--;
        return null;
    }

    @Override
    public Object visitRetunStatm(PParser.RetunStatmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.expression());
        cont--;
        return null;
    }

    @Override
    public Object visitPrintStatm(PParser.PrintStatmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName()+" : "+ctx.PRINT().getSymbol().getText());
//crear Jtextarea
        cont++;
        visit(ctx.expression());
        cont--;
        return null;
    }

    @Override
    public Object visitAssignStatm(PParser.AssignStatmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName()+" : "+ctx.IDENTIFIER().getSymbol().getText());
//crear Jtextarea
        cont++;
        visit(ctx.expression());
        cont--;
        return null;
    }

    @Override
    public Object visitFunctionStatm(PParser.FunctionStatmContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.primitiveExpression());
        visit(ctx.expressionList());
        cont--;
        return null;
    }

    @Override
    public Object visitSequencee(PParser.SequenceeContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.moreStatement());
        cont--;
        return null;

    }

    @Override
    public Object visitMoreStatementt(PParser.MoreStatementtContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.statement(0));
        for (int i=1; i <= ctx.statement().size()-1; i++)
        {
            visit(ctx.statement(i));
        }
        cont--;
        return null;

    }

    @Override
    public Object visitExpressionn(PParser.ExpressionnContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.additionExpression());
        visit(ctx.comparison());
        cont--;
        return null;
    }

    @Override
    public Object visitComparisonn(PParser.ComparisonnContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        for (int i=0; i <= ctx.additionExpression().size()-1; i++)
        {
            visit(ctx.additionExpression(i));
        }
        cont--;
        return null;
    }

    @Override
    public Object visitAdditionExpressionn(PParser.AdditionExpressionnContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.multiplicationExpression());
        visit(ctx.additionFactor());
        cont--;
        return null;
    }

    @Override
    public Object visitAdditionFactorr(PParser.AdditionFactorrContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        for (int i=0; i <= ctx.multiplicationExpression().size()-1; i++)
        {
            visit(ctx.multiplicationExpression(i));
        }
        cont--;
        return null;
    }

    @Override
    public Object visitMultiplicationExpressionn(PParser.MultiplicationExpressionnContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.elementExpression());
        visit(ctx.multiplicationFactor());
        cont--;
        return null;
    }

    @Override
    public Object visitMultiplicationFactorr(PParser.MultiplicationFactorrContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        for (int i=0; i <= ctx.elementExpression().size()-1; i++)
        {
            visit(ctx.elementExpression(i));
        }
        cont--;
        return null;
    }

    @Override
    public Object visitElementExpressions(PParser.ElementExpressionsContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.primitiveExpression());
        visit(ctx.elementAccess());
        cont--;
        return null;
    }

    @Override
    public Object visitElementAccesss(PParser.ElementAccesssContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        for (int i=0; i <= ctx.expression().size()-1; i++)
        {
            visit(ctx.expression(i));
        }
        cont--;
        return null;
    }

    @Override
    public Object visitFunctionCallStatementt(PParser.FunctionCallStatementtContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName()+" : "+ctx.IDENTIFIER().getSymbol().getText());
//crear Jtextarea
        cont++;
        visit(ctx.expressionList());
        cont--;
        return null;
    }

    @Override
    public Object visitElementExpressionn(PParser.ElementExpressionnContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.expression());
        visit(ctx.moreExpressions());
        cont--;
        return null;
    }

    @Override
    public Object visitElementExpressionVacio(PParser.ElementExpressionVacioContext ctx) {
        return null;
    }

    @Override
    public Object visitMoreExpressionss(PParser.MoreExpressionssContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
        cont++;
        for (int i=0; i <= ctx.expression().size()-1; i++)
        {
            visit(ctx.expression(i));
        }
        cont--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionInt(PParser.PrimitiveExpressionIntContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName()+" : "+ctx.INTEGER().getSymbol().getText());
        cont++;
        cont--;
//crear Jtextarea

        return null;
    }

    @Override
    public Object visitPrimitiveExpressionStr(PParser.PrimitiveExpressionStrContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName()+" : "+ctx.STRING().getSymbol().getText());
        cont++;
        cont--;
//crear Jtextarea
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionIndetifier(PParser.PrimitiveExpressionIndetifierContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName()+" : "+ctx.IDENTIFIER().getSymbol().getText());
        cont++;
        cont--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionChar(PParser.PrimitiveExpressionCharContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName()+" : "+ctx.CHAR().getSymbol().getText());
        cont++;
        cont--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionPIZQPDE(PParser.PrimitiveExpressionPIZQPDEContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.expression());
        cont--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionListEspresion(PParser.PrimitiveExpressionListEspresionContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.listExpression());
        cont--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionLENPIZQPDER(PParser.PrimitiveExpressionLENPIZQPDERContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.expression());
        cont--;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionfunctionCallExpression(PParser.PrimitiveExpressionfunctionCallExpressionContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.functionCallExpression());
        cont--;
        return null;
    }

    @Override
    public Object visitListExpressionn(PParser.ListExpressionnContext ctx) {
        printtab(cont);
        System.out.println(ctx.getClass().getSimpleName());
//crear Jtextarea
        cont++;
        visit(ctx.expressionList());
        cont--;
        return null;
    }
}
