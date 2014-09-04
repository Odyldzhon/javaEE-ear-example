--DROP TRIGGER if EXISTS delete_books_trigger on authors;
--DROP FUNCTION if EXISTS delete_books();
DROP DATABASE if EXISTS nix;
DROP SEQUENCE if EXISTS authors_id_inc;
DROP SEQUENCE if EXISTS books_id_inc;
DROP SEQUENCE if EXISTS users_id_inc;


CREATE DATABASE NIX;
\connect nix

CREATE SEQUENCE authors_id_inc
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE SEQUENCE books_id_inc
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE SEQUENCE users_id_inc
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE books(
	BOOK_ID BIGINT NOT NULL DEFAULT NEXTVAL('books_id_inc'::regclass),
	title TEXT NOT NULL,
	description TEXT NOT NULL,
	publishing_date DATE NOT NULL,
	PRIMARY KEY (BOOK_ID),
	UNIQUE (title, description, publishing_date)
);
CREATE TABLE authors(
	author_id BIGINT NOT NULL DEFAULT NEXTVAL('authors_id_inc'::regclass),
	name TEXT NOT NULL,
	surname TEXT NOT NULL,
	PRIMARY KEY (author_id),
	UNIQUE (name, surname)
);
CREATE TABLE nix_references(
	book_id BIGINT NOT NULL,
	author_id BIGINT NOT NULL,
	PRIMARY KEY(book_id, author_id),
	FOREIGN KEY (book_id)
		REFERENCES books(book_id),
	FOREIGN KEY(author_id)
		REFERENCES authors(author_id)
);
CREATE TABLE users(
	user_id BIGINT NOT NULL DEFAULT NEXTVAL('users_id_inc'::regclass),
	name TEXT NOT NULL,
	pass TEXT NOT NULL,
	PRIMARY KEY (user_id),
	UNIQUE(NAME)
);
--User can has more then one role
CREATE TABLE roles(
	user_id BIGINT NOT NULL,
	role TEXT NOT NULL,
	PRIMARY KEY(user_id, role),
	FOREIGN KEY(user_id)
		REFERENCES users(user_id)
);

/* It's not safe for entities cache but we can use this with updating JPA cache. 
CREATE FUNCTION delete_books() RETURNS trigger AS $$
	DECLARE
		books_id BIGINT[]:=ARRAY(
			SELECT result.book_id
			FROM (
				SELECT COUNT(book2auth.author_id), 
					book2auth.book_id AS book_id
				FROM nix_references cur_auth,
					nix_references book2auth
				WHERE cur_auth.author_id = OLD.author_id
				AND book2auth.book_id = cur_auth.book_id
				GROUP BY book2auth.book_id
				HAVING COUNT(book2auth.author_id)=1
			)result
		);
    BEGIN
		DELETE FROM nix_references
		WHERE book_id  = ANY (books_id);
		DELETE FROM books
		WHERE book_id  = ANY (books_id);
		RETURN OLD;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_books_trigger BEFORE DELETE ON authors
    FOR EACH ROW EXECUTE PROCEDURE delete_books();
*/
	

	