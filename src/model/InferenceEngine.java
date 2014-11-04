package model;

import java.util.Iterator;
import jess.*;

public class InferenceEngine
{
	public String fireRules(double imeca) throws JessException 
	{
		String result = "";
		Rete rete = new Rete();
		
		Iterator<Activation> acts;
		
		String cadena = "(assert (imeca (valor "+imeca+")))";
		
		rete.eval("(deftemplate imeca (slot valor))");
		
		rete.eval("(watch all)");
		rete.eval("(reset)");
		rete.eval("(defrule rule1 (imeca {valor >= 0 && valor <= 50}) => (printout t \"Bueno\" crlf))");
		rete.eval("(defrule rule2 (imeca {valor > 50 && valor <= 100}) => (printout t \"Regular\" crlf))");
		rete.eval("(defrule rule3 (imeca {valor > 100 && valor <= 150}) => (printout t \"Malo\" crlf))");
		rete.eval("(defrule rule4 (imeca {valor > 150}) => (printout t \"Muy Malo\" crlf))");
		
		rete.eval(cadena);
		
		acts = rete.listActivations();
		
		while(acts.hasNext())
		{
			Activation activo = acts.next();
			result = activo.getRule().getName();
			System.out.println(" " + result);
		}
		
		rete.eval("(run)");
		
		return result;
	}
}