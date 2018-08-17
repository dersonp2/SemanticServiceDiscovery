package br.ufma.lsdi.ssd.Interfaces;

import eu.larkc.csparql.common.RDFTuple;

import java.util.ArrayList;

/**
 * Created by Anderson on 07/06/2018.
 */

public interface Listener {
    public void update(java.util.Observable o, ArrayList<RDFTuple> rdfTuples);
}
