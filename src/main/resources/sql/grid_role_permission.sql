/*
 Navicat Premium Data Transfer

 Source Server         : YZS本地
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 192.168.1.137:3306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 27/11/2018 16:06:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grid_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `grid_role_permission`;
CREATE TABLE `grid_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `corp_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '法人编号',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2389 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid_role_permission
-- ----------------------------
INSERT INTO `grid_role_permission` VALUES (2006, NULL, 3, 4);
INSERT INTO `grid_role_permission` VALUES (2007, NULL, 3, 139);
INSERT INTO `grid_role_permission` VALUES (2008, NULL, 3, 138);
INSERT INTO `grid_role_permission` VALUES (2009, NULL, 3, 137);
INSERT INTO `grid_role_permission` VALUES (2010, NULL, 3, 136);
INSERT INTO `grid_role_permission` VALUES (2011, NULL, 3, 135);
INSERT INTO `grid_role_permission` VALUES (2012, NULL, 3, 126);
INSERT INTO `grid_role_permission` VALUES (2013, NULL, 3, 122);
INSERT INTO `grid_role_permission` VALUES (2014, NULL, 3, 118);
INSERT INTO `grid_role_permission` VALUES (2015, NULL, 3, 114);
INSERT INTO `grid_role_permission` VALUES (2016, NULL, 3, 110);
INSERT INTO `grid_role_permission` VALUES (2017, NULL, 3, 104);
INSERT INTO `grid_role_permission` VALUES (2018, NULL, 3, 92);
INSERT INTO `grid_role_permission` VALUES (2019, NULL, 3, 88);
INSERT INTO `grid_role_permission` VALUES (2020, NULL, 3, 84);
INSERT INTO `grid_role_permission` VALUES (2021, NULL, 3, 83);
INSERT INTO `grid_role_permission` VALUES (2022, NULL, 3, 82);
INSERT INTO `grid_role_permission` VALUES (2023, NULL, 3, 81);
INSERT INTO `grid_role_permission` VALUES (2024, NULL, 3, 80);
INSERT INTO `grid_role_permission` VALUES (2025, NULL, 3, 79);
INSERT INTO `grid_role_permission` VALUES (2026, NULL, 3, 78);
INSERT INTO `grid_role_permission` VALUES (2027, NULL, 3, 77);
INSERT INTO `grid_role_permission` VALUES (2028, NULL, 3, 76);
INSERT INTO `grid_role_permission` VALUES (2029, NULL, 3, 75);
INSERT INTO `grid_role_permission` VALUES (2030, NULL, 3, 74);
INSERT INTO `grid_role_permission` VALUES (2031, NULL, 3, 73);
INSERT INTO `grid_role_permission` VALUES (2032, NULL, 3, 72);
INSERT INTO `grid_role_permission` VALUES (2033, NULL, 3, 71);
INSERT INTO `grid_role_permission` VALUES (2034, NULL, 3, 70);
INSERT INTO `grid_role_permission` VALUES (2035, NULL, 3, 69);
INSERT INTO `grid_role_permission` VALUES (2036, NULL, 3, 44);
INSERT INTO `grid_role_permission` VALUES (2037, NULL, 3, 36);
INSERT INTO `grid_role_permission` VALUES (2038, NULL, 3, 32);
INSERT INTO `grid_role_permission` VALUES (2039, NULL, 3, 28);
INSERT INTO `grid_role_permission` VALUES (2040, NULL, 3, 24);
INSERT INTO `grid_role_permission` VALUES (2041, NULL, 3, 20);
INSERT INTO `grid_role_permission` VALUES (2042, NULL, 3, 16);
INSERT INTO `grid_role_permission` VALUES (2043, NULL, 3, 12);
INSERT INTO `grid_role_permission` VALUES (2044, NULL, 3, 8);
INSERT INTO `grid_role_permission` VALUES (2204, NULL, 1, 143);
INSERT INTO `grid_role_permission` VALUES (2205, NULL, 1, 4);
INSERT INTO `grid_role_permission` VALUES (2206, NULL, 1, 139);
INSERT INTO `grid_role_permission` VALUES (2207, NULL, 1, 114);
INSERT INTO `grid_role_permission` VALUES (2208, NULL, 1, 110);
INSERT INTO `grid_role_permission` VALUES (2209, NULL, 1, 138);
INSERT INTO `grid_role_permission` VALUES (2210, NULL, 1, 134);
INSERT INTO `grid_role_permission` VALUES (2211, NULL, 1, 130);
INSERT INTO `grid_role_permission` VALUES (2212, NULL, 1, 126);
INSERT INTO `grid_role_permission` VALUES (2213, NULL, 1, 122);
INSERT INTO `grid_role_permission` VALUES (2214, NULL, 1, 118);
INSERT INTO `grid_role_permission` VALUES (2215, NULL, 1, 5);
INSERT INTO `grid_role_permission` VALUES (2216, NULL, 1, 6);
INSERT INTO `grid_role_permission` VALUES (2217, NULL, 1, 7);
INSERT INTO `grid_role_permission` VALUES (2218, NULL, 1, 8);
INSERT INTO `grid_role_permission` VALUES (2219, NULL, 1, 9);
INSERT INTO `grid_role_permission` VALUES (2220, NULL, 1, 10);
INSERT INTO `grid_role_permission` VALUES (2221, NULL, 1, 11);
INSERT INTO `grid_role_permission` VALUES (2222, NULL, 1, 12);
INSERT INTO `grid_role_permission` VALUES (2223, NULL, 1, 13);
INSERT INTO `grid_role_permission` VALUES (2224, NULL, 1, 14);
INSERT INTO `grid_role_permission` VALUES (2225, NULL, 1, 15);
INSERT INTO `grid_role_permission` VALUES (2226, NULL, 1, 16);
INSERT INTO `grid_role_permission` VALUES (2227, NULL, 1, 17);
INSERT INTO `grid_role_permission` VALUES (2228, NULL, 1, 18);
INSERT INTO `grid_role_permission` VALUES (2229, NULL, 1, 19);
INSERT INTO `grid_role_permission` VALUES (2230, NULL, 1, 20);
INSERT INTO `grid_role_permission` VALUES (2231, NULL, 1, 21);
INSERT INTO `grid_role_permission` VALUES (2232, NULL, 1, 22);
INSERT INTO `grid_role_permission` VALUES (2233, NULL, 1, 23);
INSERT INTO `grid_role_permission` VALUES (2234, NULL, 1, 24);
INSERT INTO `grid_role_permission` VALUES (2235, NULL, 1, 25);
INSERT INTO `grid_role_permission` VALUES (2236, NULL, 1, 26);
INSERT INTO `grid_role_permission` VALUES (2237, NULL, 1, 27);
INSERT INTO `grid_role_permission` VALUES (2238, NULL, 1, 28);
INSERT INTO `grid_role_permission` VALUES (2239, NULL, 1, 29);
INSERT INTO `grid_role_permission` VALUES (2240, NULL, 1, 30);
INSERT INTO `grid_role_permission` VALUES (2241, NULL, 1, 31);
INSERT INTO `grid_role_permission` VALUES (2242, NULL, 1, 32);
INSERT INTO `grid_role_permission` VALUES (2243, NULL, 1, 33);
INSERT INTO `grid_role_permission` VALUES (2244, NULL, 1, 34);
INSERT INTO `grid_role_permission` VALUES (2245, NULL, 1, 35);
INSERT INTO `grid_role_permission` VALUES (2246, NULL, 1, 36);
INSERT INTO `grid_role_permission` VALUES (2247, NULL, 1, 40);
INSERT INTO `grid_role_permission` VALUES (2248, NULL, 1, 41);
INSERT INTO `grid_role_permission` VALUES (2249, NULL, 1, 42);
INSERT INTO `grid_role_permission` VALUES (2250, NULL, 1, 43);
INSERT INTO `grid_role_permission` VALUES (2251, NULL, 1, 44);
INSERT INTO `grid_role_permission` VALUES (2252, NULL, 1, 45);
INSERT INTO `grid_role_permission` VALUES (2253, NULL, 1, 46);
INSERT INTO `grid_role_permission` VALUES (2254, NULL, 1, 47);
INSERT INTO `grid_role_permission` VALUES (2255, NULL, 1, 48);
INSERT INTO `grid_role_permission` VALUES (2256, NULL, 1, 49);
INSERT INTO `grid_role_permission` VALUES (2257, NULL, 1, 50);
INSERT INTO `grid_role_permission` VALUES (2258, NULL, 1, 51);
INSERT INTO `grid_role_permission` VALUES (2259, NULL, 1, 52);
INSERT INTO `grid_role_permission` VALUES (2260, NULL, 1, 53);
INSERT INTO `grid_role_permission` VALUES (2261, NULL, 1, 54);
INSERT INTO `grid_role_permission` VALUES (2262, NULL, 1, 55);
INSERT INTO `grid_role_permission` VALUES (2263, NULL, 1, 56);
INSERT INTO `grid_role_permission` VALUES (2264, NULL, 1, 57);
INSERT INTO `grid_role_permission` VALUES (2265, NULL, 1, 58);
INSERT INTO `grid_role_permission` VALUES (2266, NULL, 1, 59);
INSERT INTO `grid_role_permission` VALUES (2267, NULL, 1, 60);
INSERT INTO `grid_role_permission` VALUES (2268, NULL, 1, 61);
INSERT INTO `grid_role_permission` VALUES (2269, NULL, 1, 62);
INSERT INTO `grid_role_permission` VALUES (2270, NULL, 1, 63);
INSERT INTO `grid_role_permission` VALUES (2271, NULL, 1, 64);
INSERT INTO `grid_role_permission` VALUES (2272, NULL, 1, 65);
INSERT INTO `grid_role_permission` VALUES (2273, NULL, 1, 66);
INSERT INTO `grid_role_permission` VALUES (2274, NULL, 1, 67);
INSERT INTO `grid_role_permission` VALUES (2275, NULL, 1, 68);
INSERT INTO `grid_role_permission` VALUES (2276, NULL, 1, 72);
INSERT INTO `grid_role_permission` VALUES (2277, NULL, 1, 76);
INSERT INTO `grid_role_permission` VALUES (2278, NULL, 1, 80);
INSERT INTO `grid_role_permission` VALUES (2279, NULL, 1, 84);
INSERT INTO `grid_role_permission` VALUES (2280, NULL, 1, 88);
INSERT INTO `grid_role_permission` VALUES (2281, NULL, 1, 92);
INSERT INTO `grid_role_permission` VALUES (2282, NULL, 1, 93);
INSERT INTO `grid_role_permission` VALUES (2283, NULL, 1, 94);
INSERT INTO `grid_role_permission` VALUES (2284, NULL, 1, 95);
INSERT INTO `grid_role_permission` VALUES (2285, NULL, 1, 96);
INSERT INTO `grid_role_permission` VALUES (2286, NULL, 1, 97);
INSERT INTO `grid_role_permission` VALUES (2287, NULL, 1, 98);
INSERT INTO `grid_role_permission` VALUES (2288, NULL, 1, 99);
INSERT INTO `grid_role_permission` VALUES (2289, NULL, 1, 100);
INSERT INTO `grid_role_permission` VALUES (2290, NULL, 1, 101);
INSERT INTO `grid_role_permission` VALUES (2291, NULL, 1, 102);
INSERT INTO `grid_role_permission` VALUES (2292, NULL, 1, 103);
INSERT INTO `grid_role_permission` VALUES (2293, NULL, 1, 104);
INSERT INTO `grid_role_permission` VALUES (2294, NULL, 2, 72);
INSERT INTO `grid_role_permission` VALUES (2295, NULL, 2, 4);
INSERT INTO `grid_role_permission` VALUES (2296, NULL, 2, 139);
INSERT INTO `grid_role_permission` VALUES (2297, NULL, 2, 61);
INSERT INTO `grid_role_permission` VALUES (2298, NULL, 2, 110);
INSERT INTO `grid_role_permission` VALUES (2299, NULL, 2, 32);
INSERT INTO `grid_role_permission` VALUES (2300, NULL, 2, 118);
INSERT INTO `grid_role_permission` VALUES (2301, NULL, 2, 134);
INSERT INTO `grid_role_permission` VALUES (2302, NULL, 2, 138);
INSERT INTO `grid_role_permission` VALUES (2303, NULL, 2, 126);
INSERT INTO `grid_role_permission` VALUES (2304, NULL, 2, 96);
INSERT INTO `grid_role_permission` VALUES (2305, NULL, 2, 104);
INSERT INTO `grid_role_permission` VALUES (2306, NULL, 2, 92);
INSERT INTO `grid_role_permission` VALUES (2307, NULL, 2, 88);
INSERT INTO `grid_role_permission` VALUES (2308, NULL, 2, 68);
INSERT INTO `grid_role_permission` VALUES (2309, NULL, 2, 64);
INSERT INTO `grid_role_permission` VALUES (2310, NULL, 2, 60);
INSERT INTO `grid_role_permission` VALUES (2311, NULL, 2, 56);
INSERT INTO `grid_role_permission` VALUES (2312, NULL, 2, 52);
INSERT INTO `grid_role_permission` VALUES (2313, NULL, 2, 48);
INSERT INTO `grid_role_permission` VALUES (2314, NULL, 2, 8);
INSERT INTO `grid_role_permission` VALUES (2315, NULL, 2, 28);
INSERT INTO `grid_role_permission` VALUES (2316, NULL, 7, 72);
INSERT INTO `grid_role_permission` VALUES (2317, NULL, 7, 143);
INSERT INTO `grid_role_permission` VALUES (2318, NULL, 7, 142);
INSERT INTO `grid_role_permission` VALUES (2319, NULL, 7, 141);
INSERT INTO `grid_role_permission` VALUES (2320, NULL, 7, 140);
INSERT INTO `grid_role_permission` VALUES (2321, NULL, 7, 139);
INSERT INTO `grid_role_permission` VALUES (2322, NULL, 7, 39);
INSERT INTO `grid_role_permission` VALUES (2323, NULL, 7, 38);
INSERT INTO `grid_role_permission` VALUES (2324, NULL, 7, 37);
INSERT INTO `grid_role_permission` VALUES (2325, NULL, 7, 40);
INSERT INTO `grid_role_permission` VALUES (2326, NULL, 7, 88);
INSERT INTO `grid_role_permission` VALUES (2327, NULL, 7, 107);
INSERT INTO `grid_role_permission` VALUES (2328, NULL, 7, 108);
INSERT INTO `grid_role_permission` VALUES (2329, NULL, 7, 109);
INSERT INTO `grid_role_permission` VALUES (2330, NULL, 7, 110);
INSERT INTO `grid_role_permission` VALUES (2331, NULL, 7, 111);
INSERT INTO `grid_role_permission` VALUES (2332, NULL, 7, 112);
INSERT INTO `grid_role_permission` VALUES (2333, NULL, 7, 113);
INSERT INTO `grid_role_permission` VALUES (2334, NULL, 7, 114);
INSERT INTO `grid_role_permission` VALUES (2335, NULL, 7, 115);
INSERT INTO `grid_role_permission` VALUES (2336, NULL, 7, 116);
INSERT INTO `grid_role_permission` VALUES (2337, NULL, 7, 117);
INSERT INTO `grid_role_permission` VALUES (2338, NULL, 7, 118);
INSERT INTO `grid_role_permission` VALUES (2339, NULL, 7, 119);
INSERT INTO `grid_role_permission` VALUES (2340, NULL, 7, 120);
INSERT INTO `grid_role_permission` VALUES (2341, NULL, 7, 121);
INSERT INTO `grid_role_permission` VALUES (2342, NULL, 7, 122);
INSERT INTO `grid_role_permission` VALUES (2343, NULL, 7, 123);
INSERT INTO `grid_role_permission` VALUES (2344, NULL, 7, 124);
INSERT INTO `grid_role_permission` VALUES (2345, NULL, 7, 125);
INSERT INTO `grid_role_permission` VALUES (2346, NULL, 7, 126);
INSERT INTO `grid_role_permission` VALUES (2347, NULL, 7, 127);
INSERT INTO `grid_role_permission` VALUES (2348, NULL, 7, 128);
INSERT INTO `grid_role_permission` VALUES (2349, NULL, 7, 129);
INSERT INTO `grid_role_permission` VALUES (2350, NULL, 7, 130);
INSERT INTO `grid_role_permission` VALUES (2351, NULL, 7, 131);
INSERT INTO `grid_role_permission` VALUES (2352, NULL, 7, 132);
INSERT INTO `grid_role_permission` VALUES (2353, NULL, 7, 133);
INSERT INTO `grid_role_permission` VALUES (2354, NULL, 7, 134);
INSERT INTO `grid_role_permission` VALUES (2355, NULL, 7, 92);
INSERT INTO `grid_role_permission` VALUES (2356, NULL, 7, 91);
INSERT INTO `grid_role_permission` VALUES (2357, NULL, 7, 90);
INSERT INTO `grid_role_permission` VALUES (2358, NULL, 7, 89);
INSERT INTO `grid_role_permission` VALUES (2359, NULL, 7, 87);
INSERT INTO `grid_role_permission` VALUES (2360, NULL, 7, 86);
INSERT INTO `grid_role_permission` VALUES (2361, NULL, 7, 85);
INSERT INTO `grid_role_permission` VALUES (2362, NULL, 7, 1);
INSERT INTO `grid_role_permission` VALUES (2363, NULL, 7, 2);
INSERT INTO `grid_role_permission` VALUES (2364, NULL, 7, 3);
INSERT INTO `grid_role_permission` VALUES (2365, NULL, 7, 4);
INSERT INTO `grid_role_permission` VALUES (2366, NULL, 5, 88);
INSERT INTO `grid_role_permission` VALUES (2367, NULL, 5, 92);
INSERT INTO `grid_role_permission` VALUES (2368, NULL, 5, 4);
INSERT INTO `grid_role_permission` VALUES (2369, NULL, 5, 139);
INSERT INTO `grid_role_permission` VALUES (2370, NULL, 5, 138);
INSERT INTO `grid_role_permission` VALUES (2371, NULL, 5, 114);
INSERT INTO `grid_role_permission` VALUES (2372, NULL, 5, 110);
INSERT INTO `grid_role_permission` VALUES (2373, NULL, 5, 84);
INSERT INTO `grid_role_permission` VALUES (2374, NULL, 5, 80);
INSERT INTO `grid_role_permission` VALUES (2375, NULL, 5, 76);
INSERT INTO `grid_role_permission` VALUES (2376, NULL, 5, 72);

SET FOREIGN_KEY_CHECKS = 1;
