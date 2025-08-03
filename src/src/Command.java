public interface Command {
    void execute();
    void printAction();

    //!method to check email format: used in AddCommand and UpdateCommand
    public default boolean checkEmail(String email){
        return true;
    }
}
