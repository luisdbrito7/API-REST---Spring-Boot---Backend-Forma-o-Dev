CREATE TABLE usuarios (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
email VARCHAR(50) NOT NULL UNIQUE,
senha VARCHAR(150) NOT NULL
);

INSERT INTO usuarios (nome, email, senha) VALUES
('Maria Eduarda', 'm4riadudex@mail.com', '$2a$12$jH0EBK1ekFDnm8lUaSjdPukvEPE2AvmcgoKbVFiaqce/UXdhddoR.'),
('Luís Fernando', 'luisdbritoo@mail.com', '$2a$12$VgNnz8swg3D4xyBBeJI3ze9QWuv2yA0CHw8dyPznKKu/MkWhUzIO2'),
('Diana Resende Brito', 'dihresendebrito@mail.com', '$2a$12$lXDhCrZGcDmc5eFBsl/XnOw58n9R4odunuaTJWA9sujoyJYoirDNa');

CREATE TABLE produtos (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
descricao VARCHAR(255),
preco DECIMAL(10,2) NOT NULL,
inserido_em TIMESTAMP NOT NULL,
categoria ENUM('ROUPA', 'ELETRONICO', 'ESCRITORIO', 'MOVEIS') NOT NULL,
id_usuario INT NOT NULL,
CONSTRAINT fk_produtos_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

INSERT INTO produtos (nome, descricao, preco, inserido_em, categoria, id_usuario) VALUES
('Smart Tv 50"', 'Smart TV 50" 4K', 2799.90, CURRENT_TIMESTAMP, 'ELETRONICO', 1),
('Camiseta Polo', 'Camiseta Polo Azul Marinho', 89.90, CURRENT_TIMESTAMP, 'ROUPA', 1),
('Livro Arquitetura Limpa', 'Livro Arquitetura Limpa - Robert C. Martin', 159.90, CURRENT_TIMESTAMP, 'ESCRITORIO', 2);