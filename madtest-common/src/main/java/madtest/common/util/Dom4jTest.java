package madtest.common.util;

import org.dom4j.Document;
import org.dom4j.Node;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by qct on 2015/7/13.
 */
public class Dom4jTest {
    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<Task xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\t<ID>cb004100-0e67-11e5-9ca9-74d4358ad8a7</ID>\n" +
                "\t<Name>render</Name>\n" +
                "\t<Type>AnimationSequence</Type>\n" +
                "\t<Project>new_project</Project>\n" +
                "\t<Environment>new_project;^&amp;</Environment>\n" +
                "\t<MantraMode>ifd</MantraMode>\n" +
                "\t<SourceFileList>\n" +
                "\t\t<SourceFile EmbedCallback=\"false\">C:\\new_project\\render.hip</SourceFile>\n" +
                "\t</SourceFileList>\n" +
                "\t<SlaveType>11158</SlaveType>\n" +
                "\t<AppInfo>\n" +
                "\t\t<Name>Houdini</Name>\n" +
                "\t\t<Version>14.0.201.13</Version>\n" +
                "\t\t<Renderer>**</Renderer>\n" +
                "\t\t<UsedPlugins/>\n" +
                "\t</AppInfo>\n" +
                "\t<AssetFileList>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"abc\" IsMissing=\"false\" Node=\"/obj/geo8/alembic1\" NodeType=\"alembic\" ServerPath=\"$HIP/abc/New SpeedTree.abc\">C:\\new_project\\abc\\New SpeedTree.abc</Asset>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"geo/obj\" IsMissing=\"false\" Node=\"/obj/geo1/file1\" NodeType=\"file\" ServerPath=\"$HIP/geo/obj/ss.obj\">C:\\new_project\\geo\\obj\\ss.obj</Asset>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"sim/bgeo\" IsMissing=\"false\" Node=\"/obj/geo2/file1\" NodeType=\"file\" ServerPath=\"$HIP/sim/bgeo/fish_sim_small_{$F4:-001-0520}.bgeo.gz\">C:\\new_project\\sim\\bgeo\\fish_sim_small_{$F4:-001-0520}.bgeo.gz</Asset>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"geo/obj\" IsMissing=\"false\" Node=\"/obj/geo3/file1\" NodeType=\"file\" ServerPath=\"$HIP/geo/obj/cut.obj\">C:\\new_project\\geo\\obj\\cut.obj</Asset>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"geo/obj\" IsMissing=\"false\" Node=\"/obj/geo4/file1\" NodeType=\"file\" ServerPath=\"$HIP/geo/obj/fae.obj\">C:\\new_project\\geo\\obj\\fae.obj</Asset>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"sim/bgeo\" IsMissing=\"false\" Node=\"/obj/geo5/file1\" NodeType=\"file\" ServerPath=\"$HIP/sim/bgeo/fish_sim_{$F4:0001-0240}.bgeo.gz\">C:\\new_project\\sim\\bgeo\\fish_sim_{$F4:0001-0240}.bgeo.gz</Asset>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"geo/bgeo\" IsMissing=\"false\" Node=\"/obj/geo6/file1\" NodeType=\"file\" ServerPath=\"$HIP/geo/bgeo/been_0006.bgeo.gz\">C:\\new_project\\geo\\bgeo\\been_0006.bgeo.gz</Asset>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"abc\" IsMissing=\"false\" Node=\"/obj/geo7/file1\" NodeType=\"file\" ServerPath=\"$HIP/abc/box.abc\">C:\\new_project\\abc\\box.abc</Asset>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"tex/jpg\" IsMissing=\"false\" Node=\"/shop/mantrasurface\" NodeType=\"vopmaterial\" ServerPath=\"$HIP/tex/jpg/2457331_004005883054_2.jpg\">C:\\new_project\\tex\\jpg\\2457331_004005883054_2.jpg</Asset>\n" +
                "\t\t<Asset ExProperty=\"AbundantFolder\" Folder=\"otls\" IsMissing=\"false\" Node=\"/obj/box_object1/sss1\" NodeType=\"sss\" ServerPath=\"$HIP/otls/fuyi.hda\">C:\\new_project\\otls\\fuyi.hda</Asset>\n" +
                "\t</AssetFileList>\n" +
                "\t<SubTaskList>\n" +
                "\t\t<SubTaskItem ID=\"cb004100-0e67-11e5-9ca9-74d4358ad8a7_1\">\n" +
                "\t\t\t<RenderLayer>/out/mantra1</RenderLayer>\n" +
                "\t\t\t<Camera>/obj/cam1</Camera>\n" +
                "\t\t\t<Renderer>mantra</Renderer>\n" +
                "\t\t\t<DupPath>false</DupPath>\n" +
                "\t\t\t<OutputLocalDir>C:\\new_project\\render\\test.$F4.exr</OutputLocalDir>\n" +
                "\t\t\t<OutputFileName>$HIP/render/test.$F4.exr</OutputFileName>\n" +
                "\t\t\t<RenderElements>0</RenderElements>\n" +
                "\t\t\t<PrefixRP>false</PrefixRP>\n" +
                "\t\t\t<FrameStart>1.0</FrameStart>\n" +
                "\t\t\t<FrameEnd>3.0</FrameEnd>\n" +
                "\t\t\t<FrameStep>1.0</FrameStep>\n" +
                "\t\t\t<FrameListString>1.0-3.0</FrameListString>\n" +
                "\t\t\t<ImageWidth>1280</ImageWidth>\n" +
                "\t\t\t<ImageHeight>720</ImageHeight>\n" +
                "\t\t\t<PreviewRender>false</PreviewRender>\n" +
                "\t\t\t<PreviewRenderStart>0</PreviewRenderStart>\n" +
                "\t\t\t<PreviewRenderStep>1</PreviewRenderStep>\n" +
                "\t\t\t<MakeMovie>false</MakeMovie>\n" +
                "\t\t\t<MakeMovieStart>0</MakeMovieStart>\n" +
                "\t\t\t<MakeMovieEnd>10</MakeMovieEnd>\n" +
                "\t\t\t<SegmentRender>false</SegmentRender>\n" +
                "\t\t\t<VRPhotonRender>false</VRPhotonRender>\n" +
                "\t\t</SubTaskItem>\n" +
                "\t</SubTaskList>\n" +
                "\t<IfdList>\n" +
                "\t\t<IfdItem>C:\\new_project\\ifd\\render0001.ifd</IfdItem>\n" +
                "\t\t<IfdItem>C:\\new_project\\ifd\\render0002.ifd</IfdItem>\n" +
                "\t\t<IfdItem>C:\\new_project\\ifd\\render0003.ifd</IfdItem>\n" +
                "\t</IfdList>\n" +
                "\t<WhenTaskComplete>\n" +
                "\t\t<NeedAssembly>false</NeedAssembly>\n" +
                "\t\t<AutoDownload>true</AutoDownload>\n" +
                "\t</WhenTaskComplete>\n" +
                "</Task>\n";

        Document document = Dom4jUtil.readXML(new ByteArrayInputStream(xml.getBytes()));
        List<Node> nodes = document.selectNodes("/Task/AppInfo/Version");
        System.out.println(nodes.get(0).getText());
    }
}
