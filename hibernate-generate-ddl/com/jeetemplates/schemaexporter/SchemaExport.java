package ch.orange.mnp.schemaexporter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;

public class SchemaExport {

    public static void main(String[] args) {
        boolean drop = false;
        boolean create = false;
        String outFile = null;
        String delimiter = "";
        String unitName = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (args[i].equals("--drop")) {
                    drop = true;
                } else if (args[i].equals("--create")) {
                    create = true;
                } else if (args[i].startsWith("--output=")) {
                    outFile = args[i].substring(9);
                } else if (args[i].startsWith("--delimiter=")) {
                    delimiter = args[i].substring(12);
                }
            } else {
                unitName = args[i];
            }
        }

        Formatter formatter = FormatStyle.DDL.getFormatter();

        Ejb3Configuration jpaConfiguration = new Ejb3Configuration().configure(unitName, null);
        Configuration hibernateConfiguration = jpaConfiguration.getHibernateConfiguration();
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/jbpm");
        properties.put("hibernate.connection.username", "jbpm");
        properties.put("hibernate.connection.password", "jbpm");
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.show_sql", "true");
        hibernateConfiguration.setProperties(properties);

        String[] createSQL = hibernateConfiguration.generateSchemaCreationScript(
                Dialect.getDialect(hibernateConfiguration.getProperties()));
        String[] dropSQL = hibernateConfiguration.generateDropSchemaScript(
                Dialect.getDialect(hibernateConfiguration.getProperties()));

        if (create) {
            export(outFile, delimiter, formatter, createSQL);
        }
        if (drop) {
            export(outFile, delimiter, formatter, dropSQL);
        }
    }

    private static void export(String outFile, String delimiter, Formatter formatter, String[] createSQL) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(outFile);
            for (String string : createSQL) {
                writer.print(formatter.format(string) + "\n" + delimiter + "\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}