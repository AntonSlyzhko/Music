It is a CRUD service that manages information about songs, artists and their albums. You can think of it as a very simplified version of Spotify.

Opportunities:
  1. CRUD operations on songs, artists and albums. Also you cannot add song/album if the artist doesn’t exist and you cannot remove artists if they have songs/albums.
  2. Get all of the songs of a given artist (by artist id).

Information of each entity displays the following data:
  Artist:
    - Id
    -	First name
    -	Last name
    -	Date of Birth
  Album
    - Id
    -	Name of the album
    -	The date when it was published
    -	First Name and Last Name of the artist
  Song
    - Id
    -	Name of the song
    -	First Name and Last Name of the artist
    -	Duration
    
    
Notes:
  -	No graphical interface is presenteed. Project is implemented as a REST service.
  - Frameworks of my choice is Spring.
  - Used SQL DB - Postgres.
