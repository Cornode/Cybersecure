package com.cornode.iri.controllers;

import com.cornode.iri.conf.Configuration;
import com.cornode.iri.model.Hash;
import com.cornode.iri.storage.Tangle;
import com.cornode.iri.storage.rocksDB.RocksDBPersistenceProvider;
import com.cornode.iri.storage.rocksDB.RocksDBPersistenceProviderTest;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

/**
 * Created by paul on 4/11/17.
 */
public class MilestoneViewModelTest {
    final TemporaryFolder dbFolder = new TemporaryFolder();
    final TemporaryFolder logFolder = new TemporaryFolder();
    private static Tangle tangle = new Tangle();
    int index = 30;

    @Before
    public void setUpTest() throws Exception {
        dbFolder.create();
        logFolder.create();
        RocksDBPersistenceProvider rocksDBPersistenceProvider;
        rocksDBPersistenceProvider = new RocksDBPersistenceProvider(dbFolder.getRoot().getAbsolutePath(),
                logFolder.getRoot().getAbsolutePath());
        tangle.addPersistenceProvider(rocksDBPersistenceProvider);
        tangle.init();
    }

    @After
    public void tearDown() throws Exception {
        tangle.shutdown();
        dbFolder.delete();
        logFolder.delete();
    }

    @Test
    public void getMilestone() throws Exception {
        Hash milestoneHash = new Hash("ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999");
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(++index, milestoneHash);
        assertTrue(milestoneViewModel.store(tangle));
        MilestoneViewModel.clear();
        MilestoneViewModel.load(tangle, index);
        assertEquals(MilestoneViewModel.get(tangle, index).getHash(), milestoneHash);
    }

    @Test
    public void store() throws Exception {
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(++index, Hash.NULL_HASH);
        assertTrue(milestoneViewModel.store(tangle));
    }

    @Test
    public void snapshot() throws Exception {
        Hash milestoneHash = new Hash("BBCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999");
        long value = 3;
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(++index, milestoneHash);
    }

    @Test
    public void initSnapshot() throws Exception {
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(++index, Hash.NULL_HASH);
    }

    @Test
    public void updateSnapshot() throws Exception {
        Hash milestoneHash = new Hash("CBCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999");
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(++index, milestoneHash);
        assertTrue(milestoneViewModel.store(tangle));
        MilestoneViewModel.clear();
        assertEquals(MilestoneViewModel.get(tangle, index).getHash(), milestoneHash);

    }

    @Test
    public void getHash() throws Exception {
        Hash milestoneHash = new Hash("DBCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999");
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(++index, milestoneHash);
        assertEquals(milestoneHash, milestoneViewModel.getHash());
    }

    @Test
    public void index() throws Exception {
        Hash milestoneHash = new Hash("EBCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999");
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(++index, milestoneHash);
        assertTrue(index == milestoneViewModel.index());
    }

    @Test
    public void latest() throws Exception {
        int top = 100;
        Hash milestoneHash = new Hash("ZBCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999");
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(top, milestoneHash);
        milestoneViewModel.store(tangle);
        assertTrue(top == MilestoneViewModel.latest(tangle).index());
    }

    @Test
    public void first() throws Exception {
        int first = 1;
        Hash milestoneHash = new Hash("99CDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999");
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(first, milestoneHash);
        milestoneViewModel.store(tangle);
        assertTrue(first == MilestoneViewModel.first(tangle).index());
    }

    @Test
    public void next() throws Exception {
        int first = 1;
        int next = 2;
        MilestoneViewModel firstMilestone = new MilestoneViewModel(first, new Hash("99CDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999"));
        firstMilestone.store(tangle);
        new MilestoneViewModel(next, new Hash("9ACDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999")).store(tangle);

        assertTrue(next == MilestoneViewModel.first(tangle).next(tangle).index());
    }

    @Test
    public void previous() throws Exception {
        int first = 1;
        int next = 2;
        MilestoneViewModel nextMilestone = new MilestoneViewModel(next, new Hash("99CDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999"));
        nextMilestone.store(tangle);
        new MilestoneViewModel(first, new Hash("9ACDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999")).store(tangle);

        assertTrue(first == nextMilestone.previous(tangle).index());
    }

    @Test
    public void latestSnapshot() throws Exception {
        int nosnapshot = 90;
        int topSnapshot = 80;
        int mid = 50;
        new MilestoneViewModel(nosnapshot, new Hash("FBCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999")).store(tangle);
        MilestoneViewModel milestoneViewModelmid = new MilestoneViewModel(mid, new Hash("GBCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999"));
        milestoneViewModelmid.store(tangle);
        MilestoneViewModel milestoneViewModeltopSnapshot = new MilestoneViewModel(topSnapshot, new Hash("GBCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999"));
        milestoneViewModeltopSnapshot.store(tangle);
        //assertTrue(topSnapshot == MilestoneViewModel.latestWithSnapshot().index());
    }

    @Test
    public void firstWithSnapshot() throws Exception {
        int first = 5;
        int firstSnapshot = 6;
        int next = 7;
        new MilestoneViewModel(first, new Hash("FBCDEFGHIJ9LMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999")).store(tangle);
        MilestoneViewModel milestoneViewModelmid = new MilestoneViewModel(next, new Hash("GBCDE9GHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999"));
        milestoneViewModelmid.store(tangle);
        MilestoneViewModel milestoneViewModeltopSnapshot = new MilestoneViewModel(firstSnapshot, new Hash("GBCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYA9ABCDEFGHIJKLMNOPQRSTUV99999"));
        milestoneViewModeltopSnapshot.store(tangle);
        //assertTrue(firstSnapshot == MilestoneViewModel.firstWithSnapshot().index());
    }

    @Test
    public void nextWithSnapshot() throws Exception {
        int firstSnapshot = 8;
        int next = 9;
        MilestoneViewModel milestoneViewModelmid = new MilestoneViewModel(next, new Hash("GBCDEBGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999"));
        milestoneViewModelmid.store(tangle);
        MilestoneViewModel milestoneViewModel = new MilestoneViewModel(firstSnapshot, new Hash("GBCDEFGHIJKLMNODQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999"));
        milestoneViewModel.store(tangle);
        //assertTrue(next == milestoneViewModel.nextWithSnapshot().index());
    }

    @Test
    public void nextGreaterThan() throws Exception {
        int first = 8;
        int next = 9;
        new MilestoneViewModel(next, new Hash("GBCDEBGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999")).store(tangle);
        new MilestoneViewModel(first, new Hash("GBCDEFGHIJKLMNODQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999")).store(tangle);
        assertEquals(next, MilestoneViewModel.findClosestNextMilestone(tangle, first).index().intValue());
    }

    @Test
    public void PrevBefore() throws Exception {
        int first = 8;
        int next = 9;
        new MilestoneViewModel(next, new Hash("GBCDEBGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999")).store(tangle);
        new MilestoneViewModel(first, new Hash("GBCDEFGHIJKLMNODQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUVWXYZ9ABCDEFGHIJKLMNOPQRSTUV99999")).store(tangle);
        assertEquals(first, MilestoneViewModel.findClosestPrevMilestone(tangle, next).index().intValue());
    }
}