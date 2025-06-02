//import java.io.ByteArrayInputStream;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
//import com.github.retrooper.packetevents.protocol.nbt.NBTLimiter;
//import com.github.retrooper.packetevents.protocol.nbt.serializer.DefaultNBTSerializer;
//
//
//public class Test {
//
//
//    public static void main(String[] args) throws IOException {
//        // Test code here
//        System.out.println("This is a test class.");
//
//        final File targetFile = new File("E:\\Minecraft\\喵喵\\bc-1.21.1\\plugins\\Freesia\\playerdata\\b4d870c8-2296-4e55-9738-e147b7bb4b8a.nbt");
//
//        byte[] bytes = Files.readAllBytes(targetFile.toPath());
//
//        final DefaultNBTSerializer serializer = new DefaultNBTSerializer();
//        final NBTCompound deserializedTag;
//        System.out.println("Deserializing NBT data...");
//
//        try {
//
//            deserializedTag = (NBTCompound) serializer.deserializeTag(NBTLimiter.forBuffer(null, Integer.MAX_VALUE), new DataInputStream(new ByteArrayInputStream(bytes)),false);
//            NBTCompound modelId = deserializedTag.getCompoundTagOrNull("model_id");
//            System.out.println("Model ID: " + (modelId != null ? modelId.toString() : "null"));
//            System.out.println(deserializedTag.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
////            throw new RuntimeException(e);
//        }
//
//
//    }
//
//
//}
