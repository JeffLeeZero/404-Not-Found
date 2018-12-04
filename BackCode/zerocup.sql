-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: zerocup
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_nickname` varchar(20) NOT NULL,
  `account_password` varchar(20) DEFAULT NULL,
  `submission_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Serendipity','111','2018-11-22 12:34:49'),(2,'艾小柯',NULL,'2018-11-24 09:31:51'),(3,'九尾黑猫',NULL,'2018-11-24 09:31:51'),(4,'犀牛',NULL,'2018-11-24 09:31:51'),(5,'文泽尔',NULL,'2018-11-24 09:31:51'),(6,'思无邪',NULL,'2018-11-24 09:31:51'),(7,'yocofcjx28',NULL,'2018-11-24 09:31:51'),(8,'七沐妹妹',NULL,'2018-11-24 09:31:51'),(9,'江山如是',NULL,'2018-11-24 09:31:51'),(10,'蝶雨成宣',NULL,'2018-11-24 09:32:07'),(11,'test','111','2018-11-29 04:43:08');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) NOT NULL,
  `movie_name` varchar(45) NOT NULL,
  `account_id` int(11) NOT NULL,
  `account_nickname` varchar(45) NOT NULL,
  `movie_score` double NOT NULL,
  `movie_comment` varchar(200) NOT NULL,
  `submission_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,'盗梦空间',1,'Serendipity',10,'一部影迷一定不要错过的电影，就像帝国杂志说的，这部影片就像是《黑客帝国》加上《纽约提喻法》，反物理学的探讨、精彩的动作场面、具有冲击力的情感、以及莱昂纳多令人吃惊的表演，都让人沉迷不已，这是诺兰电影的一个全新领域。','2018-11-22 08:20:21'),(2,1,'盗梦空间',2,'艾小柯',10,'1、电影确实精彩。2、没有想象的难懂，相信你自己。3、总会有一个让你记一辈子的镜头。4、请降低期待值。5、记得关手机。6、一定要去影院，因为影院也是个造梦机器。7、记得听完结尾音乐。因为它负责让你醒来。8、极可能是近几年你在影院看过最精彩的电影。9、穿越吧少年。','2018-11-24 10:19:31'),(3,1,'盗梦空间',3,'九尾黑猫',8,'“既然做梦，就做大点” 这句太深得人心了。——2010年最天才的电影。“谁又能说，现实不是梦境。” Do you remember how you got here?','2018-11-24 10:24:09'),(4,1,'盗梦空间',4,'犀牛',8,'前期的口碑营销确实有点儿过了，绝对还没有达到神作的级别，但依旧是近年来最好的影片，诺兰独有的叙事技巧视觉风格以及影像语言都被完美的贯彻其中，其遵循并热爱的怀古之风从人物造型，台词蔓延到拍摄手法以及特效制作上，不过正因为如此，一些动作戏上的处理还有视觉奇观的展现则还不是那么过瘾。','2018-11-24 10:24:09'),(5,1,'盗梦空间',5,'文泽尔',10,'诺兰后期的几部电影都犹如教科书般精确和经典。本片尤其如此，在所有的电影元素上都极其精准……故事层面也做到了商业和艺术的完美平衡。是我个人2010年的年度电影。','2018-11-24 10:24:09'),(6,1,'盗梦空间',6,'思无邪',8,'要想从梦境里醒过来需要重大撞击，但有时你无法分辨到底是不是在梦里，所以有了陀螺这个图腾。然而你又怎么确定你现在经历的现实不会是一个梦境？梦醒的条件大概就是离开这个世界吧。梦、梦中梦、梦与现实错综复杂，即使烧脑多看几次也会看懂的电影，值得一看。','2018-11-24 10:24:09'),(7,1,'盗梦空间',7,'yocofcjx28',10,'还是习惯叫它《奠基》。赞美的话太多人说了，不再重复。克里斯托弗·诺兰再一次证明了自己驾驭非线性叙事电影的超凡能力。虽不敢说自己百分百看懂，但是绝对没有大多数人传说的那么难懂，不过肯定是一部会需要你动脑子的影片。至于结局……仁者见仁智者见智，一千个观影者就有一千个哈姆雷特','2018-11-24 10:36:07'),(8,1,'盗梦空间',8,'七沐妹妹',10,'电影啊，造梦艺术，一部电影能拍成这样，至少在娱乐功能上已经登峰造极了，两个半小时黑漆漆的电影院里，我一直在梦里，跟我生活的现实世界完全无关。诺兰，我继续做你的粉丝。','2018-11-24 10:37:06'),(9,1,'盗梦空间',9,'江山如是',10,'你最好无须在观影前过多参与剧情解析，相信经历过原始诺兰的《追随》、疯狂倒叙的《失忆》、隐线极深的《魔道》以及道德哲学的《黑骑士》。《奠基》不会更隐晦到让你失眠。我的观影建议：享受诺兰近乎完美的画面哲学，听得懂英文的请无视字幕，不要错过一些被严肃剧情隐藏的幽默，最后，不要剧透。','2018-11-24 10:38:02'),(10,1,'盗梦空间',10,'蝶雨成宣',8,'最吸引我和令我佩服的是Nolan对细节的构造和把握，其实影片创意并不算非常新奇——当然在科幻电影里是很创新的。此外，结尾半小时左右感觉节奏有点太快了吧。还是觉得它没有达到黑客帝国或终结者2的水准。','2018-11-24 10:38:36'),(11,1,'盗梦空间',11,'test',10,'好！','2018-11-29 05:26:15'),(12,1,'盗梦空间',11,'test',10,'好！','2018-11-29 15:12:15'),(13,1,'盗梦空间',11,'test',10,'很好！','2018-11-29 15:20:41');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `constellation`
--

DROP TABLE IF EXISTS `constellation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `constellation` (
  `constellation_id` int(11) NOT NULL AUTO_INCREMENT,
  `constellation_name` varchar(12) COLLATE utf8_bin NOT NULL,
  `constellation_pic` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`constellation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `constellation`
--

LOCK TABLES `constellation` WRITE;
/*!40000 ALTER TABLE `constellation` DISABLE KEYS */;
INSERT INTO `constellation` VALUES (1,'Aries',NULL),(2,'Taurus',NULL),(3,'Gemini',NULL),(4,'Cancer',NULL),(5,'Leo',NULL),(6,'Virgo',NULL),(7,'Libra',NULL),(8,'Scorpio',NULL),(9,'Sagittarius',NULL),(10,'Capricorn',NULL),(11,'Aquarius',NULL),(12,'Pisces',NULL);
/*!40000 ALTER TABLE `constellation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie` (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `movie_score` double NOT NULL DEFAULT '10',
  `movie_intro` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `movie_slogan` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `movie_poster` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `movie_pic1` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `movie_pic2` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `movie_pic3` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `movie_background` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'盗梦空间',9.3,'影片讲述造梦师和特工团队，进入他人梦境，从他人的潜意识中盗取机密，并重塑他人梦境的故事。影片剧情游走于梦境与现实之间，被定义为“发生在意识结构内的当代动作科幻片”。影片气质也尽可能秉承了“独立片风格”：不定义忠奸善恶，游走于灰色地带，直指人心纠葛。','一颗小小的意念种子，也会生根成形。他可能成就你，也可能毁灭你。','../../BackCode/404-Not-Found/MovieData/01-盗梦空间/movie_poster','../../BackCode/404-Not-Found/MovieData/01-盗梦空间/movie_pic1','../../BackCode/404-Not-Found/MovieData/01-盗梦空间/movie_pic2','../../BackCode/404-Not-Found/MovieData/01-盗梦空间/movie_pic3','../../BackCode/404-Not-Found/MovieData/01-盗梦空间/movie_background'),(2,'请以你的名字呼唤我',8.8,'影片讲述了24岁的美国博士生奥利弗在意大利结识了17岁的少年艾利欧，两人从而发展出一段暧昧关系的故事。电影展现了一个没有歧视没有偏见的乌托邦。突如其来的爱彷佛林中奔出的野兽，攫住了17岁少年艾利欧的身与心。整部电影都是梅子苏打一样酸酸甜甜的夏日恋爱气息，看完以后，满心都是“世间好物不坚牢，彩云易散琉璃脆”的怅惘。','Call me by your name and i\'ll call you by mine.','../../BackCode/404-Not-Found/MovieData/02-请以你的名字呼唤我/movie_poster','../../BackCode/404-Not-Found/MovieData/02-请以你的名字呼唤我/movie_pic1','../../BackCode/404-Not-Found/MovieData/02-请以你的名字呼唤我/movie_pic2','../../BackCode/404-Not-Found/MovieData/02-请以你的名字呼唤我/movie_pic3','../../BackCode/404-Not-Found/MovieData/02-请以你的名字呼唤我/movie_background'),(3,'万物理论',8.2,'影片讲述了知名物理学家史蒂芬·霍金和他的第一任妻子简·王尔德始于剑桥大学的爱情故事及霍金患病前后的励志传奇。“还有什么比没有边界更特别的吗？人的努力应该是没有界限的。我们千差万别，不管生活看上去多糟，总有你能够做的事情，并且能够成功。有生命的地方，就有希望。”','While there is life, there is hope.','../../BackCode/404-Not-Found/MovieData/03-万物理论/movie_poster','../../BackCode/404-Not-Found/MovieData/03-万物理论/movie_pic1','../../BackCode/404-Not-Found/MovieData/03-万物理论/movie_pic2','../../BackCode/404-Not-Found/MovieData/03-万物理论/movie_pic3','../../BackCode/404-Not-Found/MovieData/03-万物理论/movie_background'),(4,'霸王别姬',9.6,'影片围绕两位京剧伶人半个世纪的悲欢离合，展现了对传统文化、人的生存状态及人性的思考与领悟。所谓的“霸王别姬”，虞姬是中心，而霸王只是附属，虞姬是“真虞姬”，而霸王却是“假霸王”。忘了吧，所有挥舞的拳头，忘了吧，所有疯狂的忠心。','\"说好的一辈子，差一年，一天，一个月，一个时辰，都不算一辈子。\"','../../BackCode/404-Not-Found/MovieData/04-霸王别姬/movie_poster','../../BackCode/404-Not-Found/MovieData/04-霸王别姬/movie_pic1','../../BackCode/404-Not-Found/MovieData/04-霸王别姬/movie_pic2','../../BackCode/404-Not-Found/MovieData/04-霸王别姬/movie_pic3','../../BackCode/404-Not-Found/MovieData/04-霸王别姬/movie_background'),(5,'看不见的客人',8.7,'影片讲述了企业家艾德里安在事业如日中天之时被卷入一桩谋杀案中，为了洗脱罪名，他请来了金牌女律师弗吉尼亚为自己辩护。该片构建了一出不断反转的精妙故事，将谎言的双刃性用到了极致。正如尼采所言：“与恶龙缠斗过久，自身亦成为恶龙。”影片所展现的人性的黑暗面引人深思。','欲要得到救赎，必先承受痛苦。','../../BackCode/404-Not-Found/MovieData/05-看不见的客人/movie_poster','../../BackCode/404-Not-Found/MovieData/05-看不见的客人/movie_pic1','../../BackCode/404-Not-Found/MovieData/05-看不见的客人/movie_pic2','../../BackCode/404-Not-Found/MovieData/05-看不见的客人/movie_pic3','../../BackCode/404-Not-Found/MovieData/05-看不见的客人/movie_background'),(6,'千与千寻',9.3,'影片讲述了少女千寻意外来到神灵异世界后，为了救爸爸妈妈，经历了很多磨难的故事。生命力的发掘来源于与社会的沟通，互助和关爱是打破孤独、寻回自我的钥匙。千寻的神幻历险是如此的平实，以至于每个成年人都能从她的泪水和欢笑中看见自己的童年回忆。','不管前方的路有多苦，只要走的方向正确，不管多么崎岖不平，都比站在原地更接近幸福。','../../BackCode/404-Not-Found/MovieData/06-千与千寻/movie_poster','../../BackCode/404-Not-Found/MovieData/06-千与千寻/movie_pic1','../../BackCode/404-Not-Found/MovieData/06-千与千寻/movie_pic2','../../BackCode/404-Not-Found/MovieData/06-千与千寻/movie_pic3','../../BackCode/404-Not-Found/MovieData/06-千与千寻/movie_background'),(7,'楚门的世界',9.2,'影片讲述了楚门是一档热门肥皂剧的主人公，他身边的所有事情都是虚假的，他的亲人和朋友全都是演员，但他本人对此一无所知。最终楚门不惜一切代价走出了这个虚拟的世界。影片有力地批判了“媒体万能”的价值观，用类似“乌托邦”的虚拟的完美世界寓意着“笼中鸟”式的生存悲哀。','如果再也不能见到你，祝你早安，午安，晚安。','../../BackCode/404-Not-Found/MovieData/07-楚门的世界/movie_poster','../../BackCode/404-Not-Found/MovieData/07-楚门的世界/movie_pic1','../../BackCode/404-Not-Found/MovieData/07-楚门的世界/movie_pic2','../../BackCode/404-Not-Found/MovieData/07-楚门的世界/movie_pic3','../../BackCode/404-Not-Found/MovieData/07-楚门的世界/movie_background'),(8,'丹麦女孩',8.1,'影片述了埃纳尔·韦格在妻子格尔达·韦格纳的鼓励下勇敢的接受了变性手术，成为了真正的女人的故事。世上没有两片相同的树叶，没有长相一模一样的人，这部电影最大的社会学意义是向观众展示了每个生命个体是有多么的美丽和与众不同。','\"我昨天做了一个最美的梦，梦到我是一个婴儿，躺在妈妈怀里。\"','../../BackCode/404-Not-Found/MovieData/08-丹麦女孩/movie_poster','../../BackCode/404-Not-Found/MovieData/08-丹麦女孩/movie_pic1','../../BackCode/404-Not-Found/MovieData/08-丹麦女孩/movie_pic2','../../BackCode/404-Not-Found/MovieData/08-丹麦女孩/movie_pic3','../../BackCode/404-Not-Found/MovieData/08-丹麦女孩/movie_background'),(9,'釜山行',8.3,'影片讲述单亲爸爸石宇与女儿秀安乘坐KTX高速列车往釜山，列车上由一位少女身上带来的僵尸病毒开始肆虐且不断扩散，列车于倾刻间陷入灾难的故事。在一个崩坏的、末日来临前的恐怖环境当中，人之所以为人，是出于对弱小者的庇护，对同类人的援手，对陌生人的信任，乃至于对人类阴暗面的失望，它们共同组成人类本身的丰富面貌。','花开时节再相见吧。','../../BackCode/404-Not-Found/MovieData/09-釜山行/movie_poster','../../BackCode/404-Not-Found/MovieData/09-釜山行/movie_pic1','../../BackCode/404-Not-Found/MovieData/09-釜山行/movie_pic2','../../BackCode/404-Not-Found/MovieData/09-釜山行/movie_pic3','../../BackCode/404-Not-Found/MovieData/09-釜山行/movie_background'),(10,'快把我哥带走',6.9,'影片讲述了一对日常互怼的兄妹时分和时秒的故事。该片最大程度还原了原著漫画的精髓，从妹妹的视角细腻写实地呈现剧情。除了家庭与亲情、校园与友情等多条主辅线穿插并行之外，还“创新性”地采用“我哥变她哥”的奇幻设定，加剧人物角色冲突，具有情感冲击力。','昨天一起看过星星的人，也许今天就不在身边，但是，星星却一直看着我们。','../../BackCode/404-Not-Found/MovieData/10-快把我哥带走/movie_poster','../../BackCode/404-Not-Found/MovieData/10-快把我哥带走/movie_pic1','../../BackCode/404-Not-Found/MovieData/10-快把我哥带走/movie_pic2','../../BackCode/404-Not-Found/MovieData/10-快把我哥带走/movie_pic3','../../BackCode/404-Not-Found/MovieData/10-快把我哥带走/movie_background'),(11,'海上钢琴师',9.2,'影片改编自亚利桑德罗·巴里克文学剧本《1900：独白》，讲述了一个被命名为“1900”的弃婴在一艘远洋客轮上与钢琴结缘，成为钢琴大师的传奇故事。该片是献给那些始终向往单纯、远离喧嚣的人。纯真是他们固有的属性，不会因为时间而改变。','我们笑着说再见，却深知再见遥遥无期。','../../BackCode/404-Not-Found/MovieData/11-海上钢琴师/movie_poster','../../BackCode/404-Not-Found/MovieData/11-海上钢琴师/movie_pic1','../../BackCode/404-Not-Found/MovieData/11-海上钢琴师/movie_pic2','../../BackCode/404-Not-Found/MovieData/11-海上钢琴师/movie_pic3','../../BackCode/404-Not-Found/MovieData/11-海上钢琴师/movie_background'),(12,'这个杀手不太冷',9.4,'影片讲述了一名职业杀手莱昂无意间搭救了一个全家被杀害的小女孩玛蒂尔达，他和小女孩互生情愫，最后他为了救玛蒂尔达而身亡的故事。影片主题多样，可以概括为个人与外界的格格不入、人与人之间的关系，文化的激烈碰撞和对生命的尊重，以及救赎。','——人生总是那么痛苦吗？还是只有小时候是这样？ ——总是如此。','../../BackCode/404-Not-Found/MovieData/12-这个杀手不太冷/movie_poster','../../BackCode/404-Not-Found/MovieData/12-这个杀手不太冷/movie_pic1','../../BackCode/404-Not-Found/MovieData/12-这个杀手不太冷/movie_pic2','../../BackCode/404-Not-Found/MovieData/12-这个杀手不太冷/movie_pic3','../../BackCode/404-Not-Found/MovieData/12-这个杀手不太冷/movie_background'),(13,'霍比特人',8.3,'影片讲述霍比特人比尔博·巴金斯与十三矮人收复矮人故土的故事。这部电影没有任何说教色彩，却扑面输出正能量，提炼凝聚成“信念”二字。十年过去，《指环王》系列又拥有了新的期待，中土之梦的传奇故事依旧继续着。','The world is not in your books and maps. It\'s out there.','../../BackCode/404-Not-Found/MovieData/13-霍比特人/movie_poster','../../BackCode/404-Not-Found/MovieData/13-霍比特人/movie_pic1','../../BackCode/404-Not-Found/MovieData/13-霍比特人/movie_pic2','../../BackCode/404-Not-Found/MovieData/13-霍比特人/movie_pic3','../../BackCode/404-Not-Found/MovieData/13-霍比特人/movie_background'),(14,'被嫌弃的松子的一生',8.9,'影片改编自山田宗树所著同名小说，讲述了松子渴望爱、渴望被爱而坎坷曲折的一生。她执着、倔强而又痴傻，给予男人上帝一般的爱，得到的却是拳打脚踢、满身伤痕。她终生孤独寂寞，以至临终前悲怆地写下“生而为人，我很抱歉”的遗言。','小时候，谁都觉得自己的未来闪闪发光，不是吗？但是一旦长大，没有一件事会遂自己心愿。','../../BackCode/404-Not-Found/MovieData/14-被嫌弃的松子的一生/movie_poster','../../BackCode/404-Not-Found/MovieData/14-被嫌弃的松子的一生/movie_pic1','../../BackCode/404-Not-Found/MovieData/14-被嫌弃的松子的一生/movie_pic2','../../BackCode/404-Not-Found/MovieData/14-被嫌弃的松子的一生/movie_pic3','../../BackCode/404-Not-Found/MovieData/14-被嫌弃的松子的一生/movie_background'),(15,'寻梦环游记',9,'影片讲述了热爱音乐的小男孩米格和落魄乐手埃克托在五彩斑斓的神秘世界开启了一段奇妙冒险旅程的故事。电影灵感来源于墨西哥亡灵节。离去的事实固然令人悲伤，但被人惦念、叫人怀想的那份情感却让人觉得美好温暖。在爱的记忆消失前，请记住我。','真正的死亡是世界上再没有一个人记得你。','../../BackCode/404-Not-Found/MovieData/15-寻梦环游记/movie_poster','../../BackCode/404-Not-Found/MovieData/15-寻梦环游记/movie_pic1','../../BackCode/404-Not-Found/MovieData/15-寻梦环游记/movie_pic2','../../BackCode/404-Not-Found/MovieData/15-寻梦环游记/movie_pic3','../../BackCode/404-Not-Found/MovieData/15-寻梦环游记/movie_background'),(16,'大话西游',9.2,'影片讲述了至尊宝为了救白晶晶而穿越回到五百年前，遇见紫霞仙子之后发生一段感情并最终成长为孙悟空的故事。这个世界没有什么属于你，包括你自己。也许我们就是为了创造属于自己的东西才来到这个世上，因为年轻，所以押注于爱情。该片用后现代手法对所有的事物都进行了意义消解，唯独保留了爱情。','我的意中人是个盖世英雄，有一天他会踩着七色云彩来娶我，我猜中了前头可我猜不着这结局。','../../BackCode/404-Not-Found/MovieData/16-大话西游/movie_poster','../../BackCode/404-Not-Found/MovieData/16-大话西游/movie_pic1','../../BackCode/404-Not-Found/MovieData/16-大话西游/movie_pic2','../../BackCode/404-Not-Found/MovieData/16-大话西游/movie_pic3','../../BackCode/404-Not-Found/MovieData/16-大话西游/movie_background'),(17,'爱乐之城',8.3,'影片讲述一位爵士乐钢琴家与一名怀揣梦想的女演员之间的爱情故事。以“冬-春-夏-秋-冬”为界分成五个段落，融合了爵士乐、摇摆舞，甚至是超现实的元素，让歌舞片这个日渐没落的电影类型重新焕发了生机。我们会有很多理由爱上一个人，但是核心一定是因为他身上一个闪亮的地方。如果有一天，这个闪亮的东西没有了，爱情也就随之而去了。','I\'m letting life hit me until it gets tired.Then i\'ll hit back. It\'s a classic rope-a-dope.','../../BackCode/404-Not-Found/MovieData/17-爱乐之城/movie_poster','../../BackCode/404-Not-Found/MovieData/17-爱乐之城/movie_pic1','../../BackCode/404-Not-Found/MovieData/17-爱乐之城/movie_pic2','../../BackCode/404-Not-Found/MovieData/17-爱乐之城/movie_pic3','../../BackCode/404-Not-Found/MovieData/17-爱乐之城/movie_background'),(18,'了不起的盖茨比',7.7,'影片讲述了未成名作家尼克·卡罗维深受这个纸醉金迷的上流世界及其中的幻想、爱情和谎言吸引，他目睹这种世界内、外的一切，于是决定写一个关于一段无缘的爱情、不灭的梦想和让人心痛的故事，并反映出当前的时代和挣扎的故事。','如果打算爱一个人，你要想清楚，是否愿意为了他，放弃如上帝般自由的心灵，从此心甘情愿有了羁绊。','../../BackCode/404-Not-Found/MovieData/18-了不起的盖茨比/movie_poster','../../BackCode/404-Not-Found/MovieData/18-了不起的盖茨比/movie_pic1','../../BackCode/404-Not-Found/MovieData/18-了不起的盖茨比/movie_pic2','../../BackCode/404-Not-Found/MovieData/18-了不起的盖茨比/movie_pic3','../../BackCode/404-Not-Found/MovieData/18-了不起的盖茨比/movie_background'),(19,'完美陌生人',8.6,'影片讲述了七个好友在聚会上公开手机讯息而引发的一场信任危机的故事。该片拿手机大做文章，揭露夫妻、情侣、朋友等亲密关系的脆弱性，体现比较多的是人性的复杂，或者是灰暗的那一面，更多的是欺骗与背叛。','我不希望他受伤，我要保护他。因为如果你深爱一个人，你会为他挡下全世界的子弹。','../../BackCode/404-Not-Found/MovieData/19-完美陌生人/movie_poster','../../BackCode/404-Not-Found/MovieData/19-完美陌生人/movie_pic1','../../BackCode/404-Not-Found/MovieData/19-完美陌生人/movie_pic2','../../BackCode/404-Not-Found/MovieData/19-完美陌生人/movie_pic3','../../BackCode/404-Not-Found/MovieData/19-完美陌生人/movie_background'),(20,'天堂电影院',9.1,'影片讲述了由萨瓦特利·卡西欧扮演的多多，喜欢看放映师艾佛特放电影，在胶片中找到了童年生活的乐趣的故事。这是一段属于主人公多多的人生历程，一幅西西里小镇的浓缩风情画；一部关于电影和影人的至高礼赞、一份对于爱与曾经的深远回忆。','如果你不出去走走，就会以为眼前的就是全世界。','../../BackCode/404-Not-Found/MovieData/20-天堂电影院/movie_poster','../../BackCode/404-Not-Found/MovieData/20-天堂电影院/movie_pic1','../../BackCode/404-Not-Found/MovieData/20-天堂电影院/movie_pic2','../../BackCode/404-Not-Found/MovieData/20-天堂电影院/movie_pic3','../../BackCode/404-Not-Found/MovieData/20-天堂电影院/movie_background'),(21,'哈尔的移动城堡',8.9,'影片讲述苏菲得罪了女巫被施咒变成了老太婆，进入城堡与哈尔等人度过一段奇幻经历的故事。宫崎骏的故事弘扬“真善美”，对于动画中角色来说，“最重要的”绝非金钱与声誉，往往是某个挚爱的人。影片以童趣的形式，寄托着人类的梦想和怀旧的情怀。','人老了的唯一好处就是可以失去的东西越来越少了。','../../BackCode/404-Not-Found/MovieData/21-哈尔的移动城堡/movie_poster','../../BackCode/404-Not-Found/MovieData/21-哈尔的移动城堡/movie_pic1','../../BackCode/404-Not-Found/MovieData/21-哈尔的移动城堡/movie_pic2','../../BackCode/404-Not-Found/MovieData/21-哈尔的移动城堡/movie_pic3','../../BackCode/404-Not-Found/MovieData/21-哈尔的移动城堡/movie_background'),(22,'7号房的礼物',8.7,'影片讲述了蒙受不白之冤入狱的李龙久和为了给父亲洗去冤屈而不懈努力的女儿之间的故事。人的一生就像电影一样，要好好珍惜周围的人。电影不仅直击韩国司法制度的缺陷，更予以了对智障弱势群体的关怀，当然还有那最为质朴的父爱。','纵使我一无所有，也要给你世间最美。','../../BackCode/404-Not-Found/MovieData/22-7号房的礼物/movie_poster','../../BackCode/404-Not-Found/MovieData/22-7号房的礼物/movie_pic1','../../BackCode/404-Not-Found/MovieData/22-7号房的礼物/movie_pic2','../../BackCode/404-Not-Found/MovieData/22-7号房的礼物/movie_pic3','../../BackCode/404-Not-Found/MovieData/22-7号房的礼物/movie_background'),(23,'二十二',8.7,'以2014年中国内地幸存的22位“慰安妇”的遭遇作为大背景，以个别老人和长期关爱她们的个体人员的口述，串联展现出她们的生活现状。影片不刻意煽动仇怨，质朴平和的态度另有一种引人动容的力量。每一帧都是无声的哭诉，每一秒都是那段历史的活证，每一分钟都燃烧着生命的烛光，尽管微弱，但激发起善良对善良者的同情，良知对“受侮辱和受损害的”道义支持。','这个世界真好，吃个野东西也要把命留着。','../../BackCode/404-Not-Found/MovieData/23-二十二/movie_poster','../../BackCode/404-Not-Found/MovieData/23-二十二/movie_pic1','../../BackCode/404-Not-Found/MovieData/23-二十二/movie_pic2','../../BackCode/404-Not-Found/MovieData/23-二十二/movie_pic3','../../BackCode/404-Not-Found/MovieData/23-二十二/movie_background'),(24,'唐伯虎点秋香',8.5,'影片讲述了江南才子唐伯虎对华太师府上的丫鬟秋香一见钟情，在船夫的帮助下他施展计谋混进了华府，经过一番曲折，唐伯虎终于与秋香拜堂成婚的故事。是周星驰古装喜剧片的代表作之一，被称为“后现代主义的经典之作”。香港电影“尽皆癫狂，尽皆过火”的风格可以在这部电影里找到多处注脚。','一乡二里共三夫子不识四书五经六艺竟敢教七八九子十分大胆。','../../BackCode/404-Not-Found/MovieData/24-唐伯虎点秋香/movie_poster','../../BackCode/404-Not-Found/MovieData/24-唐伯虎点秋香/movie_pic1','../../BackCode/404-Not-Found/MovieData/24-唐伯虎点秋香/movie_pic2','../../BackCode/404-Not-Found/MovieData/24-唐伯虎点秋香/movie_pic3','../../BackCode/404-Not-Found/MovieData/24-唐伯虎点秋香/movie_background');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-29 23:42:40
