package br.ufma.lsdi.ssd.Formatter;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;

import java.util.Iterator;

public class Formater {
java.lang.String m = null;

    public String[] formatter (Object arg){
        java.lang.String result[] = null;
        int i = 0;
        RDFTable q = (RDFTable)arg;
        System.out.println();
        System.out.println("-------" + q.size() + " results at SystemTime=[" + System.currentTimeMillis() + "]--------");
        Iterator i$ = q.iterator();

        while(i$.hasNext()) {
            RDFTuple t = (RDFTuple)i$.next();
            System.out.println(t.toString());
            m = t.toString();
            result[i] = m;
            i++;
        }
        return result;
    }
}
