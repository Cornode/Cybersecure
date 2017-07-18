package com.cornode.iri.controllers;

import com.cornode.iri.model.Hashes;
import com.cornode.iri.model.Hash;
import com.cornode.iri.model.Transaction;
import com.cornode.iri.storage.Indexable;
import com.cornode.iri.storage.Persistable;
import com.cornode.iri.storage.Tangle;
import com.cornode.iri.utils.Pair;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by paul on 5/6/17.
 */
public interface HashesViewModel {
    boolean store(Tangle tangle) throws Exception;
    int size();
    boolean addHash(Hash theHash);
    Indexable getIndex();
    Set<Hash> getHashes();
    void delete(Tangle tangle) throws Exception;

    HashesViewModel next(Tangle tangle) throws Exception;
}
