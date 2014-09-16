CREATE TABLE giveAways (
        id   INTEGER NOT NULL 
             PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
             (START WITH 1, INCREMENT BY 1),
        name VARCHAR(128) NOT NULL DEFAULT '',
        visible BOOLEAN DEFAULT TRUE
)


CREATE TABLE events (
        id   INTEGER NOT NULL
                PRIMARY KEY GENERATED ALWAYS AS IDENTITY
                (START WITH 1, INCREMENT BY 1),
        name VARCHAR(128),
        time INTEGER,
)

CREATE TABLE eventMembers (
        event_id INTEGER NOT NULL,
        member_id INTEGER NOT NULL,
)

CREATE TABLE members (
        id INTEGER NOT NULL,
        name VARCHAR(64)
)


CREATE TABLE eventGiveAways (
        id   INTEGER NOT NULL
                PRIMARY KEY GENERATED ALWAYS AS IDENTITY
                (START WITH 1, INCREMENT BY 1),
        event_id INTEGER NOT NULL,
        giveaway_id INTEGER NOT NULL,
        member_id INTEGER
)
