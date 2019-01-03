package com.smashstats.game;

import com.smashstats.fighters.Fighter;
import com.smashstats.stages.Stage;
import org.springframework.data.jpa.domain.Specification;

final class GameSpecifications {

    private GameSpecifications() {}

    static Specification<Game> HasFighter(Fighter fighter) {
        return (root, query, cb) -> {
            //Create query here
            if(fighter == null) {
                return null;
            }
            return cb.or(cb.equal(root.get("winner"), fighter.getId()), cb.equal(root.get("opponent"), fighter.getId()));
        };
    }

    static Specification<Game> WonBy(Fighter fighter) {
        return (root, query, cb) -> {
            //Create query here
            if(fighter == null) {
                return null;
            }
            return cb.equal(root.get("winner"), fighter.getId());
        };
    }

    static Specification<Game> OpponentIs(Fighter fighter) {
        return (root, query, cb) -> {
            //Create query here
            if(fighter == null) {
                return null;
            }
            return cb.equal(root.get("opponent"), fighter.getId());
        };
    }

    static Specification<Game> OnStage(Stage stage) {
        return (root, query, cb) -> {
            //Create query here
            if(stage == null) {
                return null;
            }
            return cb.equal(root.get("stage"), stage.getId());
        };
    }
}
