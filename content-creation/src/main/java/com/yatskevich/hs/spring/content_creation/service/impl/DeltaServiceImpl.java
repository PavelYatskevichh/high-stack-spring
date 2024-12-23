package com.yatskevich.hs.spring.content_creation.service.impl;

import com.yatskevich.hs.spring.content_creation.service.DeltaService;
import java.util.LinkedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeltaServiceImpl implements DeltaService {

    private final DiffMatchPatch diffMatchPatch;

    @Override
    public String getText2FromDelta(String text1, String delta) {
        LinkedList<DiffMatchPatch.Diff> diffsFromDelta = diffMatchPatch.diffFromDelta(text1, delta);
        return diffMatchPatch.diffText2(diffsFromDelta);
    }

    @Override
    public String getDelta(String text1, String text2) {
        LinkedList<DiffMatchPatch.Diff> diffs = diffMatchPatch.diffMain(text1, text2, true);
        diffMatchPatch.diffCleanupEfficiency(diffs);
        return diffMatchPatch.diffToDelta(diffs);
    }
}
