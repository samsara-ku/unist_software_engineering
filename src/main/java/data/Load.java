package data;

import org.apache.commons.lang3.StringUtils;

public class Load {
    private String[] categories;
    private String occupation;

    public Load(String args1, String args2) {
        this.setCategories(args1);
        this.setOccupation(this.neutralizeString(args2));
    }

    public String neutralizeString (String input) {
        return StringUtils.capitalize(input.toLowerCase());
    }

    public String[] parseCategory(String input) {
        return input.split("\\|");
    }

    public void setCategories(String categories) {
        this.categories = this.parseCategory(categories);

        for (int i=0; i<this.categoriesLength(); i++) {
            this.categories[i] = this.neutralizeString(this.categories[i]);
        }
    }

    public int categoriesLength() {
        return this.categories.length;
    }

    public String[] getCategories() {
        return this.categories;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return this.occupation;
    }
}