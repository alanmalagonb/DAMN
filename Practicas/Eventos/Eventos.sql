CREATE TABLE Asistente (
  idAsistente INTEGER PRIMARY KEY AUTOINCREMENT,
  nombreAsistente TEXT NOT NULL,
  paternoAsistente TEXT NOT NULL,
  maternoAsistente TEXT NOT NULL,
  emailAsistente TEXT NOT NULL,
  idEvento INTEGER NOT NULL,
  FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
);

CREATE TABLE Evento (
  idEvento INTEGER PRIMARY KEY AUTOINCREMENT,
  nombreEvento TEXT NOT NULL,
  descripcionEvento TEXT,
  fechaEvento DATE NOT NULL
);