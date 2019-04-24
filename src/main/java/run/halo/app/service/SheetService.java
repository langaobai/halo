package run.halo.app.service;

import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import run.halo.app.model.dto.post.SheetDetailDTO;
import run.halo.app.model.dto.post.SheetListDTO;
import run.halo.app.model.entity.Sheet;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.service.base.BasePostService;

/**
 * Sheet service interface.
 *
 * @author johnniang
 * @date 19-4-24
 */
public interface SheetService extends BasePostService<Sheet> {

    /**
     * Creates a sheet.
     *
     * @param sheet sheet must not be null
     * @return created sheet
     */
    @NonNull
    Sheet createBy(@NonNull Sheet sheet);

    /**
     * Updates a sheet.
     *
     * @param sheet sheet must not be null
     * @return updated sheet
     */
    @NonNull
    Sheet updateBy(@NonNull Sheet sheet);

    @Override
    Sheet getBy(PostStatus status, String url);

    /**
     * Converts to detail dto.
     *
     * @param sheet sheet must not be null
     * @return sheet detail dto
     */
    @NonNull
    SheetDetailDTO convertToDetailDto(@NonNull Sheet sheet);


    /**
     * Converts to list dto page.
     *
     * @param sheetPage sheet page must not be nulls
     * @return a page of sheet list dto
     */
    @NonNull
    Page<SheetListDTO> convertToListDto(@NonNull Page<Sheet> sheetPage);


}
