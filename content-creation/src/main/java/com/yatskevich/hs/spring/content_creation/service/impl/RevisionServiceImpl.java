package com.yatskevich.hs.spring.content_creation.service.impl;

import com.yatskevich.hs.spring.content_creation.dto.RevisionDataDto;
import com.yatskevich.hs.spring.content_creation.dto.RevisionDto;
import com.yatskevich.hs.spring.content_creation.entity.Content;
import com.yatskevich.hs.spring.content_creation.entity.Revision;
import com.yatskevich.hs.spring.content_creation.repository.RevisionRepository;
import com.yatskevich.hs.spring.content_creation.service.ContentService;
import com.yatskevich.hs.spring.content_creation.service.RevisionService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
    private final ContentService contentService;
    private final DiffMatchPatch diffMatchPatch;

    @Override
    public List<RevisionDto> getAllForContent(UUID contentId, UUID authorId) {
        List<Revision> revisions = revisionRepository.findAllByContentIdAndContentAuthorId(contentId, authorId);
        Content content = contentService.findContentByIdAndAuthorIdOrElseThrow(contentId, authorId);
        List<RevisionDto> revisionDtos = new ArrayList<>();

        for (Revision revision : revisions) {
            RevisionDto revisionDto = new RevisionDto();
            revisionDto.setContentId(revision.getId());
            revisionDto.setRevisionNumber(revision.getRevisionNumber());
            revisionDto.setDescription(revision.getDescription());
            revisionDto.setContentTitle(getText2FromDelta(content.getTitle(), revision.getTitleDelta()));
            revisionDto
                .setContentDescription(getText2FromDelta(content.getDescription(), revision.getDescriptionDelta()));
            revisionDto.setContentBody(getText2FromDelta(content.getBody(), revision.getBodyDelta()));
            revisionDto.setCreatedAt(revision.getCreatedAt());

            revisionDtos.add(revisionDto);
        }

        return revisionDtos;
    }

    @Override
    public void create(RevisionDataDto revisionDataDto, UUID authorId) {
        UUID contentId = revisionDataDto.getContentId();
        Content content = contentService.findContentByIdAndAuthorIdOrElseThrow(contentId, authorId);

        Optional<Revision> revisionOptional = revisionRepository.findAllByContentIdAndContentAuthorId(contentId, authorId).stream()
            .max(Comparator.comparingInt(Revision::getRevisionNumber));

        Revision revision;
        if (revisionOptional.isPresent()) {
            revision = revisionOptional.get();
            revision.setRevisionNumber(revision.getRevisionNumber() + 1);
        } else {
            revision = new Revision();
            revision.setContent(content);
            revision.setRevisionNumber(1);
            revision.setDescription(revisionDataDto.getDescription());
            revision.setTitleDelta(getDelta(content.getTitle(), revisionDataDto.getContentTitle()));
            revision.setDescriptionDelta(getDelta(content.getDescription(), revisionDataDto.getContentDescription()));
            revision.setBodyDelta(getDelta(content.getBody(), revisionDataDto.getContentBody()));
        }

        revisionRepository.save(revision);
    }

    private String getText2FromDelta(String text1, String delta) {
        LinkedList<DiffMatchPatch.Diff> diffFromDelta = diffMatchPatch.diffFromDelta(text1, delta);
        return diffMatchPatch.diffText2(diffFromDelta);
    }

    private String getDelta(String text1, String text2) {
        LinkedList<DiffMatchPatch.Diff> titleDiffs = diffMatchPatch.diffMain(text1, text2, true);
        diffMatchPatch.diffCleanupEfficiency(titleDiffs);
        return diffMatchPatch.diffToDelta(titleDiffs);
    }
}
