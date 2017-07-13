int i = 0;
        FileWriter arq = null;
        FileWriter arq1 = null;
        FileWriter arq2 = null;
        FileWriter arq3 = null;
        FileWriter arq4 = null;
        FileWriter arq5 = null;
        try {
            arq = new FileWriter("SistemaAcademico -Departamento.txt");
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter gravarArq = new PrintWriter(arq);

        for (i = 0; i <= contdep; i++) {
            gravarArq.printf("%s;%.0f%n", de[i].getNome(), de[i].getCod());

        }
        try {
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            arq1 = new FileWriter("SistemaAcademico -Curso.txt");
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        PrintWriter gravarArq1 = new PrintWriter(arq1);
        for (i = 0; i <= contcurso; i++) {
            gravarArq1.printf("%s;%.0f;%s%n", c[i].getNome(), c[i].getCod(), c[i].getDep());

        }
        try {
            arq1.close();
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            arq2 = new FileWriter("SistemaAcademico -Docente.txt");
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter gravarArq2 = new PrintWriter(arq2);

        for (i = 0; i <= contdoc; i++) {
            gravarArq2.printf("%s;%s;%s;%.0f;%s%n", p[i].getNome(), p[i].getEnd(), p[i].getCPF(), p[i].getSiape(), p[i].getDepartamento());

        }
        try {
            arq2.close();
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            arq3 = new FileWriter("SistemaAcademico -Disciplina.txt");
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        PrintWriter gravarArq3 = new PrintWriter(arq3);
        for (i = 0; i <= contdisciplina; i++) {
            gravarArq3.printf("%s;%.0f;%.0f;%.0f%n", d[i].getNome(), d[i].getCod(), d[i].getCod_curso(), d[i].getCod_prof());

        }
        try {
            arq3.close();
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            arq4 = new FileWriter("SistemaAcademico -Dicente.txt");
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        PrintWriter gravarArq4 = new PrintWriter(arq4);
        for (i = 0; i <= contaluno; i++) {
            gravarArq4.printf("%s;%s;%s;%.0f;%s%n", a[i].getNome(), a[i].getEnd(), a[i].getCPF(), a[i].getMatricula(), a[i].getCurso());
        }
        try {
            arq4.close();
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            arq5 = new FileWriter("SistemaAcademico -AlunoDisciplina.txt");
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        PrintWriter gravarArq5 = new PrintWriter(arq5);
        for (i = 0; i <= contaldi; i++) {
            gravarArq5.printf("%.0f;%.2f;%.0f%n", adi[i].getCod_aluno(), adi[i].getNotas(), adi[i].getCod_disciplina());
        }
        try {
            arq5.close();
        } catch (IOException ex) {
            Logger.getLogger(SistemaAcademico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        JOptionPane.showMessageDialog(this, "Arquivo gravado com sucesso!");

    }                               