package trivia;

enum Category{
    Pop, Science, Sports, Rock ;

        public static Category fromPosition(int position) {
        return switch (position) {
            case 0,4,8 -> Pop;
            case 1,5,9 -> Science;
            case 2,6,10 -> Sports;
            default -> Rock;
        };
    }
}
