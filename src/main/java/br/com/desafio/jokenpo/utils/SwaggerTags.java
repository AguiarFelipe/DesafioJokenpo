package br.com.desafio.jokenpo.utils;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import springfox.documentation.service.Tag;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SwaggerTags {
    public static final String MATCH_CONTROLLER_TAG = "Match";
    public static final String PLAYER_CONTROLLER_TAG = "Player";
    public static final String MOVE_CONTROLLER_TAG = "Move";
    public static final String RESULT_CONTROLLER_TAG = "Results";

    public static List<Tag> tags() {
        return Lists.newArrayList(
                new Tag(MATCH_CONTROLLER_TAG, "Endpoint for matches registration"),
                new Tag(PLAYER_CONTROLLER_TAG, "Endpoint for player registration"),
                new Tag(MOVE_CONTROLLER_TAG, "Endpoint for players to make your move"),
                new Tag(RESULT_CONTROLLER_TAG, "Endpoint to get a match result or all results")
        );
    }
}
