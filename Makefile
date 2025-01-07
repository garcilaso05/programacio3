# Configuración de variables
SRC_DIR = src
BIN_DIR = bin
DATA_DIR = data
TEXT_FILES = $(DATA_DIR)/ListaAccions.txt $(DATA_DIR)/LlistaMembres.txt
BINARY_FILES = $(DATA_DIR)/LlistaAssociacions.dat
MAIN_CLASS = consola.AppConsola
GRAPHIC_CLASS = grafica.InterficieDemostracions
TEST_CLASS = tests.UsaAssociacionsTest
JAVAC = javac
JAVA = java
RM = rm -rf
TIMEOUT = 10 # Tiempo máximo de ejecución para la terminal (en segundos)

# Encuentra todos los archivos .java en src/
SOURCES = $(shell find $(SRC_DIR) -name "*.java")
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# Objetivo por defecto
all: build init_files

# Compilación del código fuente
build: $(CLASSES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $<

# Creación de archivos de datos iniciales
init_files: $(TEXT_FILES) $(BINARY_FILES)

$(DATA_DIR)/ListaAccions.txt:
	@mkdir -p $(DATA_DIR)
	@echo "Generando archivo de acciones por defecto..."
	@echo "Xerrada;PAT100;Festa de l'esport;Quers;Marta Soler;17/01/2018;Marta Soler,Jordi Font,Núria Roca;210;6,7,8" > $@
	@echo "Demostracio;PAT105;Com es fan castells?;Pataquers;Joan Pérez;20/11/2021;false;5;1500.3" >> $@

$(DATA_DIR)/LlistaMembres.txt:
	@mkdir -p $(DATA_DIR)
	@echo "Generando archivo de miembros por defecto..."
	@echo "Alumne;Joan Pérez;joanperez@urv.cat;15/06/2020;12/11/2021;Pataquers,null,null;GEI;2;false" > $@
	@echo "Professor;Lluís Fernández;lluisfernandez@urv.cat;18/02/2016;Actiu;Pataquers,null,null;DEIM;201" >> $@

$(DATA_DIR)/LlistaAssociacions.dat:
	@mkdir -p $(DATA_22DIR)
	@echo "Generando archivo binario de asociaciones por defecto..."
	@echo -n "Pataquers;pataquers@urv.cat;GEI,GEB,GESST,externETSE,BioGEI,DG GEB-GESST;Joan Pérez;Maria García;Carles Martínez" > $@

# Ejecución del programa en terminal (con timeout)
run_terminal: build init_files
	@echo "Ejecutando terminal"
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

# Ejecución del programa gráfico
run_graphic: build init_files
	$(JAVA) -cp $(BIN_DIR) $(GRAPHIC_CLASS)


# Limpieza de archivos compilados y de datos
clean:
	$(RM) $(BIN_DIR) $(DATA_DIR)

# Ayuda
help:
	@echo "Usa los siguientes comandos:"
	@echo "  make build         Compila todo el código fuente."
	@echo "  make run_terminal  Ejecuta solo la terminal (con límite de tiempo)."
	@echo "  make run_graphic   Ejecuta solo la ventana gráfica."
	@echo "  make run_both      Ejecuta terminal (con límite) y ventana gráfica a la vez."
	@echo "  make init_files    Genera los archivos de datos iniciales."
	@echo "  make clean         Limpia todos los archivos compilados y de datos."
	@echo "  make help          Muestra este mensaje de ayuda."

.PHONY: all build run_terminal run_graphic run_both clean help init_files
