import java.util.Random;

public class test {
    public static void main(String[] args) {
        String[] str = {"政治与法学学院、法学、知识产权、监狱学、政治学与行政学、",
                "体育学院、运动训练、体育教育、运动康复、社会体育指导与管理" ,
                "中国语言文学学院、汉语言文学、汉语言、汉语言文学教育、中文应用" ,
                "外国语言文学学院、西班牙语、韩语、日语、英语师范" ,
                "新闻传播学院、新闻学、广播电视学、广告学、传播学" ,
                "音乐学院、音乐教育、音乐学、音乐表演、舞蹈学" ,
                "美术与设计学院、书法学、艺术学理论、美术学、设计学" ,
                "数学学院、数学示范、应用数学、信息与计算科学、数理基础科学" ,
                "物理与电子科学学院、热力学与统计物理、力学、热学、电磁学、光学" ,
                "化学化工学院、高分子化工工艺、高分子材料与工程、化学工程与工艺、应用化学" ,
                "机械工程学院、、机械设计制造及其自动化、材料成型及控制工程、机械工程及自动化、铁道机车车辆" ,
                "信息科学与工程学院、电气工程及自动化、计算机科学与技术、通信工程、电子信息工程、信息工程、软件工程" ,
                "土木建筑工程学院、建筑工程、桥梁工程、地下工程、岩土工程" ,
                "经济与管理学院、会计、经济学、金融、国际贸易、旅游管理"};

        Random random = new Random();
            String name = "顾、项、陈、刘、王、李、肖、伍、余、沈、韩、杨、鲁、韦、昌、马、薛、尹";
            String[] ns = name.split("、");

        for (int i = 0; i < str.length; i++) {
//            System.out.println(str[i].split("、"));
            String[] s1 = str[i].split("、");
            long Colleage = 1000000000L;
                 Colleage+= i*1000000L;
            int index = 1;
            for(int j = 1;j<s1.length;j++){
//                System.out.println(j+"55555");
                if(j==1)
                Colleage+= 111111L;
                String s2 = s1[j];
                for (int k = 1; k <= 2; k++) {
                    for (int l = 0; l < 5; l++) {
                        int sex = random.nextInt(2)+1;
                        String s3 = k+"班";
                        String in = "insert into student(Sno,Sname,Sex,Age,Colleage,Major,classes) values("+Colleage+","+ns[i]+index+
                                ","+sex+","+"18"+","+s1[0]+","+s1[j]+","+s1[j]+s3+")";
                        index++;
                        Colleage++;
                        System.out.println(in);
                    }

                }
            }

        }
    }
}
