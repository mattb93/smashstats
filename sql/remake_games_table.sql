DROP TABLE IF EXISTS Games;

CREATE TABLE Games (
	id INT NOT NULL AUTO_INCREMENT,
	winner_id INT NOT NULL,
    opponent_id INT NOT NULL,
	stage_id INT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (winner_id) references Fighters(id),
	FOREIGN KEY (opponent_id) references Fighters(id),
	FOREIGN KEY (stage_id) references Stages(id));