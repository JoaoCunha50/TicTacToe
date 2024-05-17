# Variáveis para o diretório de origem e destino
SRC_DIR := src
BUILD_DIR := build
JAR_DIR := jogo

# Nome do jar
JAR_NAME := TicTacToe.jar

# Comando para compilar os arquivos .java para .class
COMPILE := javac -d $(BUILD_DIR) -sourcepath $(SRC_DIR)

# Comando para criar o jar
JAR := jar -cfm $(JAR_DIR)/$(JAR_NAME) manifest.txt -C $(BUILD_DIR) .

# Lista de todos os arquivos .java no diretório src
JAVA_FILES := $(wildcard $(SRC_DIR)/*.java)

# Transforma a lista de arquivos .java em uma lista de arquivos .class
CLASS_FILES := $(patsubst $(SRC_DIR)/%.java,$(BUILD_DIR)/%.class,$(JAVA_FILES))

# Alvo padrão para a compilação
all: $(CLASS_FILES) jar

# Regra para compilar os arquivos .java em .class
$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java
	$(COMPILE) $<

# Regra para criar o diretório de build
$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

# Regra para criar o diretório do jar
$(JAR_DIR):
	mkdir -p $(JAR_DIR)

# Alvo para criar o jar
jar: $(BUILD_DIR) $(JAR_DIR) $(CLASS_FILES)
	$(JAR)

# Alvo para limpar todos os arquivos compilados
clean:
	rm -rf $(BUILD_DIR) $(JAR_DIR)
