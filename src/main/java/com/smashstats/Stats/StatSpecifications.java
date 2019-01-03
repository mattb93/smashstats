package com.smashstats.Stats;

import com.smashstats.fighters.Fighter;
import com.smashstats.game.Game;
import com.smashstats.stages.Stage;
import org.springframework.data.jpa.domain.Specification;

final class StatSpecifications {

    private StatSpecifications(){}

    static Specification<Game> CountAllWins(Fighter fighter) {
        return (root, query, cb) -> {
            //Create query here
            if(fighter == null) {
                return null;
            }
            query.groupBy(root.get("opponent"));
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
