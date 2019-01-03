package com.smashstats.game;

import com.smashstats.fighters.Fighter;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Integer>, JpaSpecificationExecutor<Game> {

    // SELECT opponent_id, COUNT(*) FROM games WHERE winner_id = 12 GROUP BY opponent_id order by opponent_id
    @Query("SELECT g.opponent, COUNT(g) FROM Game g WHERE g.winner.id = :winnerId GROUP BY g.opponent.id ORDER BY g.opponent.id")
    public List<Object[]> countWinsGroupByOpponent(@Param("winnerId") Integer winnerId);

    @Query("SELECT g.winner, COUNT(g) FROM Game g WHERE g.opponent.id = :opponentId GROUP BY g.winner.id ORDER BY g.winner.id")
    public List<Object[]> countLossesGroupByOpponent(@Param("opponentId") Integer opponentId);
}
