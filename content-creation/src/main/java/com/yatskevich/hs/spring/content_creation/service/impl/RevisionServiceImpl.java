package com.yatskevich.hs.spring.content_creation.service.impl;

import com.yatskevich.hs.spring.content_creation.dto.RevisionDataDto;
import com.yatskevich.hs.spring.content_creation.repository.RevisionRepository;
import com.yatskevich.hs.spring.content_creation.service.RevisionService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RevisionServiceImpl implements RevisionService {

    private final RevisionRepository revisionRepository;
    private final DiffMatchPatch diffMatchPatch;

    @Override
    public void getAll(UUID contentId, UUID authorId) {

    }

    @Override
    public void create(RevisionDataDto revisionDataDto, UUID authorId) {

    }

//    String version1 = "Emotional intelligence includes communication skills. This guide helps you understand and develop these traits.";
//    String version2 = "Emotional intelligence includes self-awareness, empathy, and communication skills. This guide helps you understand and develop these traits.";
//
//    DiffMatchPatch diffMatchPatch = new DiffMatchPatch();
//
//    LinkedList<DiffMatchPatch.Diff> diffMain1 = diffMatchPatch.diffMain(version1, version2, true);
//    diffMatchPatch.diffCleanupEfficiency(diffMain1);
//    String delta = diffMatchPatch.diffToDelta(diffMain1);
//
//    LinkedList<DiffMatchPatch.Diff> diffFromDelta1 = diffMatchPatch.diffFromDelta(version1, delta);
//    String recovered = diffMatchPatch.diffText2(diffFromDelta1);
}
